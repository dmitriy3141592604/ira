package utils;

import static java.util.Arrays.asList;
import static utils.BuiltinArraysUtils.isBuiltinArray;

import java.util.ArrayList;
import java.util.Collection;

@Responsibility("Формирует линейный список элементов из дерева, содержащего коллекции коллекций объектов")
/** TODO Обеспечить работу с Collection **/
public class ExpandInnerArray {

	/** Извлекает из многоуровнего массива массивов линейный список объектов */
	public Iterable<Object> expand(Object[] objects) {
		return expand(new ArrayList<Object>(), objects);
	}

	private Collection<Object> expand(Collection<Object> acc, Object... objects) {
		asList(objects).forEach(object -> {
			if (isBuiltinArray(object)) {
				expand(acc, (Object[]) object);
			} else {
				acc.add(object);
			}
		});
		return acc;
	}

}
