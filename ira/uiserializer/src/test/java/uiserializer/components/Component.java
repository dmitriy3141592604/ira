package uiserializer.components;

import org.i2g.ira.uibuilder.HTMLElements;

/**
 * Компонент, который может себя отрисовать как html сущность.
 *
 */
public interface Component {

	/**
	 * Отрисовывает себя
	 *
	 * @param html
	 *            корень html дерева, в который будет помещено содержимое
	 *            компонента
	 */
	void render(HTMLElements html);

}