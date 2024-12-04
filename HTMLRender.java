/**
 *	HTMLRender
 *	This program renders HTML code into a JFrame window.
 *	It requires your HTMLUtilities class and
 *	the SimpleHtmlRenderer and HtmlPrinter classes.
 *
 *	The tags supported:
 *		<html>, </html> - start/end of the HTML file
 *		<body>, </body> - start/end of the HTML code
 *		<p>, </p> - Start/end of a paragraph.
 *					Causes a newline before and a blank line after. Lines are restricted
 *					to 80 characters maximum.
 *		<hr>	- Creates a horizontal rule on the following line.
 *		<br>	- newline (break)
 *		<b>, </b> - Start/end of bold font print
 *		<i>, </i> - Start/end of italic font print
 *		<q>, </q> - Start/end of quotations
 *		<hX>, </hX> - Start/end of heading with size X = 1, 2, 3, 4, 5, 6
 *		<pre>, </pre> - Preformatted text
 *
 *	@author Kshitij Kashyap Kumble
 *	@version 1.0
 */
public class HTMLRender {
	
	// the array holding all the tokens of the HTML file
	private String [] tokens;
	private final int TOKENS_SIZE = 100000;	// size of array
    private int token_ctr = 0;

	// SimpleHtmlRenderer fields
	private SimpleHtmlRenderer render;
	private HtmlPrinter browser;
	private HTMLUtilities html_utility;
		
	public HTMLRender() {
		// Initialize token array
		tokens = new String[TOKENS_SIZE];

		// Initialize Simple Browser
		render = new SimpleHtmlRenderer();
		browser = render.getHtmlPrinter();
	}

    /**
     * Main function of HTMLRender
     * @param args arg[0] input file name for html rendering
     */
	public static void main(String[] args) {
        HTMLRender hf = new HTMLRender();
        if (args[0] == null) {
            System.out.println("Please re-run the program with the input file name as one of the arguments");
		}

        if (args[0].isEmpty()) {
            System.out.println("Please re-run the program with the input file name as one of the arguments");
        }

        // Get example file from java command
        String filename = args[0];

        // Use HTMLUtilities to Get Tokens from a HTML file
        HTMLUtilities html_utility = new HTMLUtilities();
        hf.token_ctr = html_utility.tokenizeHTMLString(filename, hf.tokens);

        System.out.println("counter" + hf.token_ctr);
        // temporary display the tokens
        for (int i = 0; i < hf.token_ctr; i++) {
            if (hf.tokens[i] != null) {
                //System.out.println(hf.tokens[i]);
            }
        }

		hf.run();
            
    }

    /**
     * run - Method to invoke the browser printing
     */
	public void run() {

             printHr(tokens, token_ctr);

        /*
		// Sample renderings from HtmlPrinter class
		
		// Print plain text without line feed at end
		browser.print("First line");
		
		// Print line feed
		browser.println();
		
		// Print bold words and plain space without line feed at end
		browser.printBold("bold words");
		browser.print(" ");
		
		// Print italic words without line feed at end
		browser.printItalic("italic words");
		
		// Print horizontal rule across window (includes line feed before and after)
		browser.printHorizontalRule();
		
		// Print words, then line feed (printBreak)
		browser.print("A couple of words");
		browser.printBreak();
		browser.printBreak();
		
		// Print a double quote
		browser.print("\"");
		
		// Print Headings 1 through 6 (Largest to smallest)
		browser.printHeading1("Heading1");
		browser.printHeading2("Heading2");
		browser.printHeading3("Heading3");
		browser.printHeading4("Heading4");
		browser.printHeading5("Heading5");
		browser.printHeading6("Heading6");
		
		// Print pre-formatted text (optional)
		browser.printPreformattedText("Preformat Monospace\tfont");
		browser.printBreak();
		browser.print("The end");
                */
	}

    /**
     * HtmlPrinter - Method to return the handle to the HTMLRender object
     * @return  HTMLRender object
     */
    public HtmlPrinter getHtmlPrinter() {
        return browser;
    }

    /**
     * printHr - Method to handle various tags for printing on the drowser
     * @param token_arr  Array of tokens
     * @param arr_len    Length of Array of tokens
     */
    public void printHr (String [] token_arr, int arr_len) {
        int render_token_ctr = 0;
        String nl =  String.valueOf('\n');

        while(arr_len > render_token_ctr) {
            System.out.println( render_token_ctr + " " +token_arr[render_token_ctr]);
            int skip_ctr = 0;
            switch (token_arr[render_token_ctr].toLowerCase()) {
                case "<html>" :
                    //print_dump_string("<html>", token_arr[render_token_ctr], 80);
                    // skip_ctr = print_tag(token_arr, render_token_ctr,"<html>", "</html>", 80);
                    break;
                case "<body>" :
                    //print_dump_string("<body>", token_arr[render_token_ctr], 80);
                    //skip_ctr = print_tag(token_arr, render_token_ctr,"<body>", "</body>", 80);
                    break;
                case "<p>" :
                    skip_ctr = print_tag(token_arr, render_token_ctr,"<p>", "</p>", 80);
                    break;
                case "<b>" :
                    skip_ctr = print_tag(token_arr, render_token_ctr, "<b>", "</b>", 80);
                    break;
                case "<i>" :
                    skip_ctr =  print_tag(token_arr, render_token_ctr, "<i>", "</i>", 80);
                    break;
                case "<q>" :
                    skip_ctr = print_tag(token_arr,render_token_ctr, "<q>", "</q>", 80);
                    break;
                case "<pre>" :
                    skip_ctr = print_tag(token_arr, render_token_ctr, "<pre>", "</pre>", 65535);
                    break;
                case "<h1>" :
                    skip_ctr = print_tag(token_arr, render_token_ctr, "<h1>", "</h1>", 40);
                    break;
                case "<h2>" :
                    skip_ctr = print_tag(token_arr, render_token_ctr, "<h2>", "</h2>", 50);
                    break;
                case "<h3>" :
                    skip_ctr = print_tag(token_arr, render_token_ctr, "<h3>", "</h3>", 60);
                    break;
                case "<h4>" :
                    skip_ctr = print_tag(token_arr, render_token_ctr, "<h4>", "</h4>", 80);
                    break;
                case "<h5>" :
                    skip_ctr = print_tag(token_arr, render_token_ctr, "<h5>", "</h5>", 100);
                    break;
                case "<h6>" :
                    skip_ctr = print_tag(token_arr, render_token_ctr, "<h6>", "</h6>", 120);
                    break;
                case "<nl>" :
                    browser_dump_string("<nl>", "");
                    break;
                case "<br>" :
                    browser_dump_string("<br>", "");
                    break;
                case "<hr>" :
                    browser_dump_string("<hr>", "");
                    break;
                default:
                    break;
            }
            if(token_arr[render_token_ctr].toLowerCase().equals(nl)) {
                browser.println();
            }
            render_token_ctr+=skip_ctr;
            render_token_ctr++;  // case string 
        }

        return;
    }

/*
Tags Name Nested tags
<html>, </html>
<body>, </body> begin HTML
HTML body
all other tags
<p>, </p> paragraph tags <b>, </b>; <i>, </i>; <br>, <hr>
<q>, </q> quotation tags <br>; <hr>
<b>, </b> bold tags <br>; <hr>
<i>, </i> italic tags <br>; <hr>
<hr> horizontal rule no nested tags
<h1>, </h1>
<h2>, </h2>
…
<h6>, </h6>
heading tags no nested tags
<pre>, </pre> preformatted text tags no nested tags
*/

    /**
     * print_tag - Method to prepare dump string for all tokens between start and end tags
     * @param token_arr Array of tokens
     * @param start_token_ctr Index of the start token
     * @param start_tag  The start tag to focus on
     * @param end_tag    The end tag to focus on
     * @param line_maxlen Max len for each line
     * @return Count of tokens that completed printing
     */
    public int print_tag(String [] token_arr , int start_token_ctr,
                         String start_tag, String end_tag, int line_maxlen) {
        boolean start_b_tag = false;
        boolean start_i_tag = false;

        int skip_p_tag_ctr = 0;
        String dump_string = "";
        System.out.println(start_token_ctr + "*start* " + token_arr[start_token_ctr]);
        // skip the start tag
        start_token_ctr++;  
        while ((!token_arr[start_token_ctr].toLowerCase().equalsIgnoreCase(end_tag))) {
            System.out.println("dump: " + start_token_ctr + " " +token_arr[start_token_ctr]);
            if (token_arr[start_token_ctr].toLowerCase().equalsIgnoreCase("<br>")) {
                print_dump_string(start_tag, dump_string, line_maxlen);
                browser.printBreak();
                dump_string = "";
                // count how  many token have been clubbed into dump string
                skip_p_tag_ctr++;
                // Move array index
                start_token_ctr++;
                continue;
            } 
            if (token_arr[start_token_ctr].toLowerCase().equalsIgnoreCase("<hr>")) {
                print_dump_string(start_tag, dump_string, line_maxlen);
                browser.printHorizontalRule();
                dump_string = "";
                // count how  many token have been clubbed into dump string
                skip_p_tag_ctr++;
                // Move array index
                start_token_ctr++;
                continue;
            } 
            if (token_arr[start_token_ctr].toLowerCase().equalsIgnoreCase("<b>")) {
                start_b_tag = true;
            }
            if (token_arr[start_token_ctr].toLowerCase().equalsIgnoreCase("</b>")) {
                start_b_tag = false;
                print_dump_string("<b>", dump_string, line_maxlen);
                dump_string = "";
                // count how  many token have been clubbed into dump string
                skip_p_tag_ctr++;
                // Move array index
                start_token_ctr++;
                continue;
            }
            if (token_arr[start_token_ctr].toLowerCase().equalsIgnoreCase("<i>")) {
                start_i_tag = true;
            }
            if (token_arr[start_token_ctr].toLowerCase().equalsIgnoreCase("</i>")) {
                start_i_tag = false;
                print_dump_string("<i>", dump_string, line_maxlen);
                dump_string = "";
                // count how  many token have been clubbed into dump string
                skip_p_tag_ctr++;
                // Move array index
                start_token_ctr++;
             }
            if (token_arr[start_token_ctr].toLowerCase().equalsIgnoreCase("<nl>")) {
                print_dump_string(start_tag, dump_string, line_maxlen);
                dump_string = "";
                // count how  many token have been clubbed into dump string
                skip_p_tag_ctr++;
                // Move array index
                start_token_ctr++;
                continue;
            }

            dump_string += token_arr[start_token_ctr];
            // add blank not before a "."
            if (!token_arr[start_token_ctr+1].equals(".")) {
                dump_string += " ";
            }

            // count how  many token have been clubbed into dump string
            skip_p_tag_ctr++;
            // Move array index
            start_token_ctr++;
        }
        skip_p_tag_ctr++; // skip end tag

        print_dump_string(start_tag, dump_string, line_maxlen);

        return (skip_p_tag_ctr);
    }


    public void print_dump_string(String start_tag, String dump_string, int line_maxlen) { 
        System.out.println("gen dump str: " + dump_string);
        //handle if more than 80 lines
        if (dump_string.length() >= line_maxlen) {
            String temp_sub1 = dump_string.substring(0, line_maxlen);
            String temp_sub2 = dump_string.substring(line_maxlen, dump_string.length());
            browser_dump_string(start_tag, temp_sub1);
            browser_dump_string(start_tag, temp_sub2);
            System.out.println("dump str:" + temp_sub1);
            System.out.println("dump str: " + temp_sub2);
        } else {
            System.out.println("dump str: " + dump_string);
            browser_dump_string(start_tag, dump_string);
        }
    }

    /**
     * browser_dump_string - Method to use the right apu to dump the string based on the tag
     * @param start_tag   Start tag
     * @param dump_string String to pront on browser
     */
    public void  browser_dump_string(String start_tag, String dump_string) {
            String nl =  String.valueOf('\n');

            switch (start_tag) {
                case "<p>" :
                    browser.print(dump_string);
                    break;
                case "<nl>" :
                    browser.println();
                    break;
                case "<b>" :
                    browser.printBold(dump_string);
                    break;
                case "<i>" :
                    browser.printItalic(dump_string);
                    break;
                case "<q>" :
                    browser.print("\"" + dump_string + "\"");
                    break;
                case "<pre>" :
                    browser.printPreformattedText(dump_string);
                    break;
                case "<h1>" :
                    browser.printHeading1(dump_string);
                    break;
                case "<h2>" :
                    browser.printHeading2(dump_string);
                    break;
                case "<h3>" :
                    browser.printHeading3(dump_string);
                    break;
                case "<h4>" :
                    browser.printHeading4(dump_string);
                    break;
                case "<h5>" :
                    browser.printHeading5(dump_string);
                    break;
                case "<h6>" :
                    browser.printHeading6(dump_string);
                    break;
                case "<br>" :
                    browser.printBreak();
                    break;
                case "<hr>" :
                    browser.printHorizontalRule();
                    break;
                default:
                    break;
        }
        if (start_tag.equals(nl)) {
            browser.println();
        }
    }
}
