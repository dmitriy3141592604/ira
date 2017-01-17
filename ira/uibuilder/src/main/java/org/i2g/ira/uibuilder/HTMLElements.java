package org.i2g.ira.uibuilder;

public interface HTMLElements {
	HTMLElements a(Attribute... attrs);

	HTMLElements body(Attribute... attrs);

	HTMLElements button(Attribute... attrs);

	HTMLElements caption(Attribute... attrs);

	HTMLElements div(Attribute... attrs);

	HTMLElements form(Attribute... attrs);

	HTMLElements h1(Attribute... attributes);

	HTMLElements h2(Attribute... attributes);

	HTMLElements h3(Attribute... attributes);

	HTMLElements h4(Attribute... attributes);

	HTMLElements h5(Attribute... attributes);

	HTMLElements h6(Attribute... attributes);

	HTMLElements h7(Attribute... attributes);

	HTMLElements h8(Attribute... attributes);

	HTMLElements head(Attribute... attrs);

	HTMLElements input(Attribute... attributes);

	HTMLElements label(Attribute... attrs);

	HTMLElements li(Attribute... attrs);

	HTMLElements link(Attribute... attrs);

	HTMLElements meta(Attribute charset);

	HTMLElements script(Attribute... attrs);

	HTMLElements table(Attribute... attributes);

	HTMLElements td(Attribute... attrs);

	HTMLElements text(String text);

	HTMLElements title(Attribute... attrs);

	HTMLElements tr(Attribute... attrs);

	HTMLElements ul(Attribute... attrs);

}
