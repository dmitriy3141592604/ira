package structure;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import utils.Behavior;

public class MetaSupportTest extends MetaSupportTestBase {

	@Test
	@Behavior("Всегда можно определить наличие маркера")
	public void test$markerPresent() {
		mt.mark(marker);
		assertEquals(true, mt.hasMarker(marker));
	}

	@Test
	@Behavior("Наличие маркера не зависит от ассоциированного с ним значения")
	public void test$markerPresentIndependedFromItValue() {
		mt.markWith(marker, FALSE);
		assertEquals(true, mt.hasMarker(marker));
	}

	@Test
	@Behavior("В общем случае, маркер нужно установить, что бы он был")
	public void test$byDefaultMarkerNotPresent() {
		assertEquals(false, mt.hasMarker(marker));
	}

	@Test
	@Behavior("У маркера есть значение")
	public void test$markerCanHaveValue() {
		mt.markWith(marker, value);
		assertEquals(value, mt.getMarker(marker, randomString()));
	}

	@Test
	@Behavior("По умолчанию значением маркера является Boolean.TRUE")
	public void test$markerValueExtractorUseDefaultValueIfMarkerNotSet() {
		mt.mark(marker);
		assertEquals(TRUE, mt.getMarker(marker, FALSE));
	}

	@Test
	@Behavior("Если маркер не установлен, то используется заданное значение по умолчанию")
	public void test$ifMarkerNotSetMarkerValueExtractorUseDefaultValue() {
		assertEquals(defaultValue, mt.getMarker(marker, defaultValue));
	}

	@Test(expected = MarkerException.PreviousMarkerHasDifferentTypeException.class)
	@Behavior("Если маркер был установлен, то значение по умолчанию должно быть такого же типа")
	public void test$markerValueExtractorCheckPreviousMarkerClass() {
		mt.markWith(marker, randomString());
		mt.getMarker(marker, randomInteger());
	}

	@Test
	@Behavior("Маркер можно перезаписать его предыдущим значением")
	public void test$markerValueCanSetMultipleTimesWithOneValue() {
		mt.markWith(marker, new AlwaysEqual());
		mt.markWith(marker, new AlwaysEqual());
	}

	@Test(expected = MarkerException.PreviousMarkerValueRewriteException.class)
	@Behavior("Значение установленного маркера менять нельзя")
	public void test$markerValueCanNotBeChangedToOther() {
		mt.markWith(marker, new AlwaysNotEquals());
		mt.markWith(marker, new AlwaysNotEquals());
	}

	@Test(expected = MarkerException.PreviousMarkerValueRewriteException.class)
	@Behavior("Значение установленного маркера нельзя сбрасывать")
	public void test$markerResetNotAllowed() {
		mt.markWith(marker, randomString());
		mt.markWith(marker, null);
	}

	@Test(expected = NullPointerException.class)
	@Behavior("Значение маркера нельзя устанавливать в null")
	public void test$canNotMarkWithNull() {
		mt.markWith(marker, null);
	}

}
