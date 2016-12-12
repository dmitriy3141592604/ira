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

	HTMLElements tr(Attribute... attrs);

	HTMLElements td(Attribute... attrs);

	HTMLElements table(Attribute... attributes);

	HTMLElements h1(Attribute... attributes);

	HTMLElements h2(Attribute... attributes);

	HTMLElements h3(Attribute... attributes);

	HTMLElements h4(Attribute... attributes);

	HTMLElements h5(Attribute... attributes);

	HTMLElements h6(Attribute... attributes);

	HTMLElements h7(Attribute... attributes);

	HTMLElements h8(Attribute... attributes);

}
