package structure;

import utils.Responsibility;

@Responsibility("Фиксирует возможность доступа к мета информации об объекте")
public interface WithMarkersSupport {

	/**
	 * Обеспечивает доступ к метаинформации об объекте
	 */
	MarkerSupport getMetaInfo();
}
