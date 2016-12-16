package org.i2g.ira.uibuilder;

import utils.Responsibility;

@Responsibility("Предоставляет интерфейс HTML тега")
public interface Tag {

	@Responsibility("Обеспечивает добавление дочернего тега")
	public Tag addChield(Tag tag);

	@Responsibility("Обеспечивает обход существующего дерева тегов")
	public void visit(TagVisitor visitor);

}
