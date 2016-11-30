package utils;

import java.util.ArrayList;
import java.util.Collection;

@Responsibility("Формирует линейный список элементов из дерева, содержащего коллекции коллекций объектов")
/** TODO Обеспечить работу с Collection **/
public class ExpandInnerArray {

	public Object[] expand(Object[] objects3) {
		return expand(objects3, new ArrayList<Object>()).toArray(new Object[0]);
	}

	private Collection<Object> expand(Object objects, Collection<Object> acc) {
		// TODO Выделить 2016.12.01 общий метод (startsWith 'class ["
		if (objects.getClass().toString().charAt(6) != '[') {
			acc.add(objects);
		} else {
			for (final Object o : (Object[]) objects) {
				if (o.getClass().toString().charAt(6) != '[') {
					acc.add(o);
				} else {
					expand(o, acc);
				}
			}
		}
		return acc;
	}

}
