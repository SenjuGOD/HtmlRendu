import java.io.File;
import java.util.Scanner;

/**
 * HTMLUtilities is a class that
 * Reads from a file or takes an input from a file.
 * Tokenise the content.
 * Read each character including < and > and only start copying the word from <
 * Stop copying the word when you reach >
 * Store this newly formed String into a temp string
 * Put this into an String array so all new strings are kept here
 */




public class HTMLUtilities {
    /**
     * Store the tokens from the file read.
     */
    String[] html_tokenArray = new String[6000];
    int html_token_ctr = 0;

    /**
     * Main function to start the utility
     *
     * @param args
     */
    public static void main(String[] args) {
        HTMLUtilities bored = new HTMLUtilities();
        //Open a file
        String[] new_tokenArray = new String[600];
        if (!args[0].isEmpty()) {
            int ctr = bored.tokenizeHTMLString(args[0], new_tokenArray);
            //display the tokens
            for (int i = 0; i < ctr; i++) {
                if (new_tokenArray[i] != null) {
                    //System.out.println(" " + i + " " + new_tokenArray[i].toLowerCase());
                    //clear the tokens
                    new_tokenArray[i] = "";
                }
            }
        }
    }

    /**
     * getTokens - Tokenise the input and
     *             update field tokenArray
     *
     * @param inputStr   - Input string
     */
    public int getTokens(String inputStr, String[] tokenArray, int token_ctr) {
        String recorded_str = "";
        boolean tag_on = false;
        char C = 'A';
        char C_2 = 'Z';
        char c = 'a';
        char c_2 = 'z';

        for (int i = 0; i < inputStr.length(); i++) {

            // case 1: if between <>
            // if found < and not yet found >
            char cur_char = inputStr.charAt(i);
            char next_char = 0;
            if (i + 1 < inputStr.length()) {
                next_char = inputStr.charAt(i + 1);
            }
            //System.out.println(cur_char);
            // Check start of tag
            if (cur_char == '<') {
                tag_on = true;
                //**k if not empy
                if (!recorded_str.isEmpty()) {
                    token_ctr = separatePuncWord(recorded_str, tokenArray, token_ctr);
                    //flush the recorded temp string
                    recorded_str = "";
                }
                //**start collect char for tag
                //System.out.println("rec_str1 " +recorded_str);
                recorded_str += inputStr.charAt(i);
                //go to next char
                continue;
            }

            // Check end of tag
            if (cur_char == '>') {
                tag_on = false;
                //**k collect char for tag
                tokenArray[token_ctr] = recorded_str + ">";
                //System.out.println("rec_str2 " + tokenArray[token_ctr] + " " + token_ctr);
                token_ctr++;
                recorded_str = "";
                continue;
            }
            // Check if between a tag
            if (tag_on == true) {
                //collect char for tag when not < nor > but between
                recorded_str += inputStr.charAt(i);
                //go to next char
                continue;
            }

            //case 2: if !between <>
            // capture all into one string
            if ((cur_char != '<') && (cur_char != '>') && (cur_char != ' ')) {
                // it is a word now
                recorded_str += inputStr.charAt(i);
            } else {
                token_ctr = separatePuncWord(recorded_str, tokenArray, token_ctr);
                recorded_str = "";
            }
        }
        if (!recorded_str.isEmpty()) {
            token_ctr = separatePuncWord(recorded_str, tokenArray, token_ctr);
            recorded_str = "";
        }
        return token_ctr;

    }//end of function

    public int separatePuncWord(String recorded_str, String[] tokenArray, int token_ctr){
        // if blank collect word
        // case 3: check if last char of rec str is a punctuation separate it out
        // dont care, if "-" sign in the beginning, treat it as same string
        int x = recorded_str.length();
        char end_char = 0;
        if (x - 1 >= 0) {
            end_char = recorded_str.charAt(x - 1);
        }
        boolean is_punc= isPunctuationToken(end_char);
        if (is_punc) {
            // Separating word and punctuation
            //Get word
            tokenArray[token_ctr] = recorded_str.substring(0, recorded_str.length()-1);
            //System.out.println("rec_str3 " + tokenArray[token_ctr ]+ " " + token_ctr );
            token_ctr++;
            //System.out.println("sep word :" + recorded_str.substring(0, recorded_str.length()-1));

            //Get punctuation
            tokenArray[token_ctr] = recorded_str.substring(recorded_str.length()-1);
            //System.out.println("rec_str4 " + tokenArray[token_ctr]+ " " + token_ctr);
            //System.out.println("sep :" + recorded_str.substring(recorded_str.length()-1));
            token_ctr++;

        } else {
            //case 4: simple word
            // store into array
            tokenArray[token_ctr] = recorded_str;
            //System.out.println("rec_str5 " + tokenArray[token_ctr] + " " + token_ctr);
            token_ctr++;
            //flush the recoreded temp string

        }
        //System.out.println("punc rec_str " +recorded_str);
        return token_ctr;
    }



    public boolean isPunctuationToken (char input_char) {
        switch (input_char) {
            case '.':
            case ',':
            case ';':
            case ':':
            case '(', ')':
            case '?':
            case '!':
            case '=':
            case '&':
            case '~':
            case '+':
            case '-':
                return true;
            default:
                return false;
        }
    }

    public int tokenizeHTMLString(String filename, String[]  tokenArray) {

        System.out.println("************** Working on " + filename + " **************");
        Scanner scanner = FileUtils.openToRead(filename);
        int token_ctr = 0;
        String nls = "<nl>";
        while(scanner.hasNextLine()) {
            String inputStr = scanner.nextLine();

            // get tokens for the str
            token_ctr = getTokens(inputStr, tokenArray, token_ctr);

            //Add New line
            tokenArray[token_ctr] = nls;
            token_ctr++;
        }
        return token_ctr;
    }
}
