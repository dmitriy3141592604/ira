package uiserializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uiserializer.components.Component;

// FIXME Переименовать в ComponentBase
public abstract class ComponentBuilderBase implements Component {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

}