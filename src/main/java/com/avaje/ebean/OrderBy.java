package com.avaje.ebean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents an Order By for a Query.
 * <p>
 * Is a ordered list of OrderBy.Property objects each specifying a property and
 * whether it is ascending or descending order.
 * </p>
 * <p>
 * Typically you will not construct an OrderBy yourself but use
 * one that exists on the Query object.
 * </p>
 * 
 * @author rbygrave
 */
public final class OrderBy<T> implements Serializable {

    private static final long serialVersionUID = 9157089257745730539L;

    private transient Query<T> query;

	private List<Property> list;

	/**
	 * Create an empty OrderBy with no associated query.
	 */
	public OrderBy() {
		this.list = new ArrayList<Property>(2);
	}

	/**
	 * Create an orderBy parsing the order by clause.
	 * <p>
	 * The order by clause follows SQL order by clause with comma's between each
	 * property and optionally "asc" or "desc" to represent ascending or
	 * descending order respectively.
	 * </p>
	 */
	public OrderBy(String orderByClause) {
		this(null, orderByClause);
	}

	/**
	 * Construct with a given query and order by clause.
	 */
	public OrderBy(Query<T> query, String orderByClause) {
		this.query = query;
		this.list = new ArrayList<Property>(2);
		parse(orderByClause);
	}

	/**
	 * Reverse the ascending/descending order on all the properties.
	 */
	public void reverse() {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).reverse();
		}
	}

	/**
	 * Add a property with ascending order to this OrderBy.
	 */
	public Query<T> asc(String propertyName) {

		list.add(new Property(propertyName, true));
		return query;
	}

	/**
	 * Add a property with descending order to this OrderBy.
	 */
	public Query<T> desc(String propertyName) {

		list.add(new Property(propertyName, false));
		return query;
	}

	/**
	 * Return the properties for this OrderBy.
	 */
	public List<Property> getProperties() {
		// not returning an Immutable list at this point
		return list;
	}

	/**
	 * Return true if this OrderBy does not have any properties.
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/**
	 * Return the associated query if there is one.
	 */
	public Query<T> getQuery() {
		return query;
	}

	/**
	 * Associate this OrderBy with a query.
	 */
	public void setQuery(Query<T> query) {
		this.query = query;
	}

	/**
	 * Return a copy of the OrderBy.
	 */
	public OrderBy<T> copy() {

		OrderBy<T> copy = new OrderBy<T>();
		for (int i = 0; i < list.size(); i++) {
			copy.add(list.get(i).copy());
		}
		return copy;
	}

	/**
	 * Add a property to the order by.
	 */
	public void add(Property p) {
		list.add(p);
	}

	public String toString() {
		return list.toString();
	}

	/**
	 * Returns the OrderBy in string format.
	 */
	public String toStringFormat() {
		if (list.isEmpty()) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			Property property = list.get(i);
			if (i > 0) {
				sb.append(", ");
			}
			sb.append(property.toStringFormat());
		}
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof OrderBy<?>){
			if (obj == this){
				return true;
			}
			OrderBy<?> other = (OrderBy<?>)obj;
			return hashCode() == other.hashCode();
		}
		return false;
	}

	@Override
	public int hashCode() {
		return hash();
	}
	
	/**
	 * Return a hash value for this OrderBy.
	 * This can be to determine logical equality for OrderBy clauses.
	 */
	public int hash() {
		int hc = OrderBy.class.getName().hashCode();
		for (int i = 0; i < list.size(); i++) {
			hc = hc * 31 + list.get(i).hash();
		}
		return hc;
	}

	/**
	 * A property and its ascending descending order.
	 */
	public static final class Property implements Serializable {

        private static final long serialVersionUID = 1546009780322478077L;

        private String property;

		private boolean ascending;

		public Property(String property, boolean ascending) {
			this.property = property;
			this.ascending = ascending;
		}
        
		protected int hash() {
			int hc = property.hashCode();
			hc = hc * 31 + (ascending? 0 : 1);
			return hc;
		}
		
		public String toString() {
			return toStringFormat();
		}

		public String toStringFormat() {
			if (ascending) {
				return property;
			} else {
				return property + " desc";
			}
		}

		/**
		 * Reverse the ascending/descending order for this property.
		 */
		public void reverse() {
			this.ascending = !ascending;
		}

		/**
		 * Trim off the pathPrefix.
		 */
		public void trim(String pathPrefix){
		    property = property.substring(pathPrefix.length() + 1);
		}
		
		/**
		 * Return a copy of this property.
		 */
		public Property copy() {
			return new Property(property, ascending);
		}

		/**
		 * Return the property name.
		 */
		public String getProperty() {
			return property;
		}

		/**
		 * Set the property name.
		 */
		public void setProperty(String property) {
			this.property = property;
		}

		/**
		 * Return true if the order is ascending.
		 */
		public boolean isAscending() {
			return ascending;
		}

		/**
		 * Set to true if the order is ascending.
		 */
		public void setAscending(boolean ascending) {
			this.ascending = ascending;
		}

	}

	private void parse(String orderByClause) {

		if (orderByClause == null) {
			return;
		}

		String[] chunks = orderByClause.split(",");
		for (int i = 0; i < chunks.length; i++) {

			String[] pairs = chunks[i].split(" ");
			Property p = parseProperty(pairs);
			if (p != null) {
				list.add(p);
			}
		}
	}

	private Property parseProperty(String[] pairs) {
		if (pairs.length == 0) {
			return null;
		}

		ArrayList<String> wordList = new ArrayList<String>(pairs.length);
		for (int i = 0; i < pairs.length; i++) {
			if (!isEmptyString(pairs[i])) {
				wordList.add(pairs[i]);
			}
		}
		if (wordList.isEmpty()) {
			return null;
		}
		if (wordList.size() == 1) {
			return new Property(wordList.get(0), true);
		}
		if (wordList.size() == 2) {
			boolean asc = isAscending(wordList.get(1));
			return new Property(wordList.get(0), asc);
		}
		String m = "Expecting a max of 2 words in [" + Arrays.toString(pairs)
				+ "] but got " + wordList.size();
		throw new RuntimeException(m);
	}

	private boolean isAscending(String s) {
		s = s.toLowerCase();
		if (s.startsWith("asc")) {
			return true;
		}
		if (s.startsWith("desc")) {
			return false;
		}
		String m = "Expecting [" + s + "] to be asc or desc?";
		throw new RuntimeException(m);
	}

	private boolean isEmptyString(String s) {
		return s == null || s.length() == 0;
	}
}
