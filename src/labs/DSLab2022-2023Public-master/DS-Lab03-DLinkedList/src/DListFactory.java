public class DListFactory<T> {

	public List<T> getDList()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		return getDList("DLinkedList");
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<T> getDList(String classNameToTest) {
		Class<?> c;
		List<T> dlist=null;
		try {
			c = Class.forName(classNameToTest);
			dlist = (List<T>) c.newInstance();
			
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return dlist;
	}

}
