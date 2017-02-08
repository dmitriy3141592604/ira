package application;

import utils.Responsibility;

@Responsibility("Предоставляет возможность задать новое имя объекта")
public interface WithMutableName {

	void setName(String newName);

}
