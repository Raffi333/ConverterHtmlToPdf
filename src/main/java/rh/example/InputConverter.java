package rh.example;

import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;
import org.jsoup.select.NodeFilter;

import java.util.stream.Collectors;

public class InputConverter {


    public static void inputsConverter(Document doc) {
        Elements inputElements = doc.select("input");
        setCheckboxCheckedAttr(inputElements);
        typeRadioChangeToCheckbox(inputElements);
        changeInputByTypeTextToSpan(inputElements);
//        setStyleForInputByTypeText(inputElements);

        Elements textareaElements = doc.select("textarea");
        changeTextareaToSpan(textareaElements);

        Elements selectElements = doc.select("select");
        changeSelectToSpas(selectElements);


        System.out.println("ok");
    }


    public static void setCheckboxCheckedAttr(Elements inputElements) {
        /*
         * if input is
         * <input type="checkbox" checked=""> OR <input type="checkbox" checked>
         * should be
         * <input type="checkbox" checked="checked">
         * */
        for (Element input : inputElements) {
            Attributes attributes = input.attributes();
            if (attributes.get("type").equals("checkbox")) {
                closeCheckedAttr(attributes);
            }
        }
    }

    public static void closeCheckedAttr(Attributes attributes) {
        if (attributes.hasKey("checked") && !attributes.get("checked").equals("checked")) {
            attributes.put("checked", "checked");
        }
    }

    public static void typeRadioChangeToCheckbox(Elements inputElements) {
        /*
         * if input is
         * <input type="radio"> OR <input type="radio" checked=""> OR <input type="radio" checked>
         * should be
         * <input type="checkbox"> OR <input type="checkbox" checked="checked">
         * */
        for (Element input : inputElements) {
            Attributes attributes = input.attributes();
            if (attributes.get("type").equals("radio")) {
                attributes.put("type", "checkbox");
                closeCheckedAttr(attributes);
            }
        }
    }

    public static void setStyleForInputByTypeText(Elements inputElements) {
        /*
         *If input type is text set style="width:400px;height:30px"
         * */

        for (Element input : inputElements) {
            Attributes attributes = input.attributes();
            if (attributes.get("type").equals("text")) {
                StringBuilder styles = new StringBuilder();
                if (!attributes.get("style").isEmpty()) {
                    styles.append(attributes.get("style"));
                    styles.append(attributes.get(";"));
                }
                styles.append("width:100px !important");
                styles.append(";height:30px !important");
                styles.append(";padding:3px !important");
                attributes.put("style", styles.toString());
            }
        }
    }

    public static void changeTextareaToSpan(Elements textareaElements) {
        /*
         * */

        for (Element textarea : textareaElements) {
            textarea.tagName("span");
        }
    }

    public static void changeSelectToSpas(Elements selectElements) {
        for (Element select : selectElements) {
            Elements options = select.select("option");
            StringBuilder textSelected = new StringBuilder("");
            for (Element option : options) {
                if (option.attributes().hasKey("selected")) {
                    textSelected.append(option.text());
                    textSelected.append(",");
                }
                option.remove();
            }
            if (textSelected.charAt(textSelected.length() - 1) == ',') {
                textSelected.deleteCharAt(textSelected.length() - 1);
            }
            select.tagName("span");
            select.appendText(textSelected.toString());
        }
    }

    public static void changeInputByTypeTextToSpan(Elements inputElements) {
        /*
         * */

        for (Element input : inputElements) {
            Attributes attributes = input.attributes();
            if (attributes.get("type").equals("text")) {
                input.tagName("span");
                input.appendText(input.attributes().get("value"));
            }
        }
    }
}



