package org.i2g.ira.uibuilder;

public interface HTMLElements {

	HTMLElements form(Attribute... attrs);

	HTMLElements input(Attribute... attributes);

	HTMLElements label(Attribute... attrs);

	HTMLElements text(String text);

	HTMLElements head(Attribute... attrs);

	HTMLElements title(Attribute... attrs);

	HTMLElements meta(Attribute charset);

	HTMLElements link(Attribute... attrs);

	HTMLElements body(Attribute... attrs);

	HTMLElements div(Attribute... attrs);

	HTMLElements ul(Attribute... attrs);

	HTMLElements li(Attribute... attrs);

	HTMLElements script(Attribute... attrs);

}
