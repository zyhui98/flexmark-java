package com.vladsch.flexmark.test;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.internal.util.DataHolder;
import com.vladsch.flexmark.internal.util.KeepType;
import com.vladsch.flexmark.internal.util.MutableDataSet;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.spec.SpecExample;
import com.vladsch.flexmark.spec.SpecReader;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComboExtraSpecTest extends ComboSpecTestCase {
    static final String SPEC_RESOURCE = "/extra_ast_spec.md";
    private static final DataHolder OPTIONS = new MutableDataSet()
            .set(HtmlRenderer.INDENT_SIZE, 2)
            .set(HtmlRenderer.PERCENT_ENCODE_URLS, true);

    private static final Map<String, DataHolder> optionsMap = new HashMap<>();
    static {
        optionsMap.put("keep-last", new MutableDataSet().set(Parser.REFERENCES_KEEP, KeepType.LAST));
        optionsMap.put("relaxed-emphasis", new MutableDataSet().set(Parser.INLINE_RELAXED_EMPHASIS, true));
        optionsMap.put("hdr-no-atx-space", new MutableDataSet().set(Parser.HEADERS_NO_ATX_SPACE, true));
        optionsMap.put("hdr-no-lead-space", new MutableDataSet().set(Parser.HEADERS_NO_LEAD_SPACE, true));
        optionsMap.put("list-fixed-indent", new MutableDataSet().set(Parser.LISTS_FIXED_INDENT, 4));
        optionsMap.put("list-no-break", new MutableDataSet().set(Parser.LISTS_END_ON_DOUBLE_BLANK, false));
        optionsMap.put("list-no-loose", new MutableDataSet().set(Parser.LISTS_AUTO_LOOSE, false));
        optionsMap.put("list-no-start", new MutableDataSet().set(HtmlRenderer.LISTS_ORDERED_START, false));
        optionsMap.put("list-no-bullet-match", new MutableDataSet().set(Parser.LISTS_BULLET_MATCH, false));
        optionsMap.put("list-no-relaxed-start", new MutableDataSet().set(Parser.LISTS_RELAXED_START, false));
        optionsMap.put("hr-no-relaxed-start", new MutableDataSet().set(Parser.THEMATIC_BREAK_RELAXED_START, false));
        optionsMap.put("escape-html", new MutableDataSet().set(HtmlRenderer.ESCAPE_HTML, true));
        optionsMap.put("escape-html-blocks", new MutableDataSet().set(HtmlRenderer.ESCAPE_HTML_BLOCKS, true));
        optionsMap.put("escape-html-comment-blocks", new MutableDataSet().set(HtmlRenderer.ESCAPE_HTML_COMMENT_BLOCKS, true));
        optionsMap.put("escape-inline-html", new MutableDataSet().set(HtmlRenderer.ESCAPE_INLINE_HTML, true));
        optionsMap.put("escape-inline-html-comments", new MutableDataSet().set(HtmlRenderer.ESCAPE_INLINE_HTML_COMMENTS, true));
        optionsMap.put("suppress-html", new MutableDataSet().set(HtmlRenderer.SUPPRESS_HTML, true));
        optionsMap.put("suppress-html-blocks", new MutableDataSet().set(HtmlRenderer.SUPPRESS_HTML_BLOCKS, true));
        optionsMap.put("suppress-html-comment-blocks", new MutableDataSet().set(HtmlRenderer.SUPPRESS_HTML_COMMENT_BLOCKS, true));
        optionsMap.put("suppress-inline-html", new MutableDataSet().set(HtmlRenderer.SUPPRESS_INLINE_HTML, true));
        optionsMap.put("suppress-inline-html-comments", new MutableDataSet().set(HtmlRenderer.SUPPRESS_INLINE_HTML_COMMENT, true));
    }

    static final Parser PARSER = Parser.builder(OPTIONS).build();
    // The spec says URL-escaping is optional, but the examples assume that it's enabled.
    static final HtmlRenderer RENDERER = HtmlRenderer.builder(OPTIONS).build();

    static DataHolder optionsSet(String optionSet) {
        return optionsMap.get(optionSet);
    }

    public ComboExtraSpecTest(SpecExample example) {
        super(example);
    }

    @Parameterized.Parameters(name = "{0}")
    public static List<Object[]> data() {
        List<SpecExample> examples = SpecReader.readExamples(SPEC_RESOURCE);
        List<Object[]> data = new ArrayList<>();

        // NULL example runs full spec test
        data.add(new Object[] { SpecExample.NULL });

        for (SpecExample example : examples) {
            data.add(new Object[] { example });
        }
        return data;
    }

    @Override
    protected DataHolder options(String optionSet) {
        return optionsSet(optionSet);
    }

    @Override
    protected String getSpecResourceName() {
        return SPEC_RESOURCE;
    }

    @Override
    protected Parser parser() {
        return PARSER;
    }

    @Override
    protected HtmlRenderer renderer() {
        return RENDERER;
    }
}
