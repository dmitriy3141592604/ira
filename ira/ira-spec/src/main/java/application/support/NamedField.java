package application.support;

import utils.Responsibility;

@Responsibility("Предоставляет интерфейс именованного поля для UI")
public interface NamedField extends WithId, WithLabel {

}
