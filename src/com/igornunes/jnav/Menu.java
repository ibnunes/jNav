/*
 * Copyright (C) 2022 Igor Nunes
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, specifically version 2 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.igornunes.jnav;

import java.util.ArrayList;
import java.util.function.Function;

import com.igornunes.intf.*;
import com.igornunes.util.Read;

/**
 * @author Igor Nunes
 */
public class Menu implements Navigation<MenuItem> {
    final static public String DEFAULT_NOOPTION = "Option not in the menu.";
    final static public String DEFAULT_ERRMSG   = "That is not an option.";
    final static public String DEFAULT_PROMPT_FORMAT = "%d > %s";

    final static public Thunk VOID = Thunk.NOTHING;      // Just to be able to write Menu.VOID, which is nicer to read

    // Properties of the class
    private String title;
    private String promptFormat;
    private ArrayList<MenuItem> items;
    private short index = 0;
    private short exit = 0;

    /**
     * @return the title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the items
     */
    public ArrayList<MenuItem> getItems() {
        return this.items;
    }

    /**
     * @return the exit
     */
    public short getExit() {
        return this.exit;
    }

    /**
     * @return flag that indicates if there is an exit option
     */
    public boolean hasExit() {
        return this.exit != 0;
    }

    /**
     * @param items the items to set
     */
    public void setItems(ArrayList<MenuItem> items) {
        this.items.clear();
        this.index = 0;
        for (MenuItem item : items) {
            this.items.add(item.setPrompt(String.format(this.promptFormat, ++this.index, item.getPrompt())));
        }
    }

    /**
     * @param item the item to add
     * @return the instance itself
     */
    public Menu addItem(MenuItem item) {
        return this.addItem(item, false);
    }

    /**
     * @param item the item to add
     * @param isExit flag that indicates if this is the exit option
     * @return the instance itself
     */
    public Menu addItem(MenuItem item, boolean isExit) {
        this.items.add(item.setPrompt(String.format(this.promptFormat, ++this.index, item.getPrompt())));
        if (isExit) this.exit = this.index;
        return this;
    }

    /**
     * @param prompt the prompt to set
     * @param procedure the procedure to set
     */
    public void addItem(String prompt, Thunk procedure) {
        this.addItem(prompt, procedure, false);
    }

    /**
     * @param prompt the prompt to set
     * @param procedure the procedure to set
     * @param isExit flag that indicates if this is the exit option
     */
    public void addItem(String prompt, Thunk procedure, boolean isExit) {
        this.items.add(new MenuItem(String.format("%d > %s", ++this.index, prompt), procedure));
        if (isExit) this.exit = this.index;
    }

    /**
     * Prints the menu to the screen
     */
    public void show() {
        System.out.print(this);
    }

    /**
     * @param PROMPT the prompt to show the user asking for a menu option
     * @param NOOPTION the message to show if an unavailable option is selected
     * @param ERRMSG the message to show if an error occurs while getting an option
     * @return the option to choose from the menu
     */
    public short getOption(final String PROMPT, final String NOOPTION, final String ERRMSG) {
        Function<Short, Boolean> condition = n -> (n > 0) && (n <= this.index);
        short option = 0;

        do {
            try {
                System.out.printf("%s", PROMPT);
                option = Read.asShort();
                if (!condition.apply(option)) {
                    System.out.println(NOOPTION);
                }
            } catch (Exception e) {
                System.out.println(ERRMSG);
            }
        } while (!condition.apply(option));

        return option;
    }

    public short getOption(final String prompt) {
        return this.getOption(prompt, Menu.DEFAULT_NOOPTION, Menu.DEFAULT_ERRMSG);
    }

    /**
     * @param option the option non zero indexed to be executed
     * @throws Exception
     */
    public void execute(short option) throws Exception {
        this.items.get(option-1).getProcedure().apply();
    }

    /**
     * Specifies a new prompt format to be used. It must include no more than an integer (%d, the menu item index)
     * and a string (%s, the menu item description).
     * @param format The format, as a String, to be used to format the prompt.
     */
    public void setPromptFormat(String format) {
        this.promptFormat = format;
    }

    /**
     * @return the prompt format.
     */
    public String getPromptFormat() {
        return this.promptFormat;
    }

    /**
     * @return the String representation of the class
     */
    @Override
    public String toString() {
        String output = String.format("%s\n", this.title);
        for (MenuItem item : this.items) {
            output += String.format("%s\n", item);
        }
        return output;
    }

    /**
     * Constructor of the class
     */
    public Menu() {
        this.items = new ArrayList<>();
        this.title = "";
        this.promptFormat = Menu.DEFAULT_PROMPT_FORMAT;
    }

    /**
     * Constructor of the class
     * @param title the initial title to set
     */
    public Menu(String title) {
        this.items = new ArrayList<>();
        this.title = title;
        this.promptFormat = Menu.DEFAULT_PROMPT_FORMAT;
    }

    /**
     * Generic method to run the menus of this application.
     * @param menu the menu to be run
     */
    public static void runMenu(Menu menu) {
        if (!menu.hasExit())
            System.exit(-1);

        short option;
        do {
            menu.show();
            option = menu.getOption("Option: ", Menu.DEFAULT_NOOPTION, Menu.DEFAULT_ERRMSG);
            System.out.printf("\n");    // just for aesthetics
            try {
                menu.execute(option);
            } catch (Exception e) {
                Menu.printExceptionOnMenu(option, menu.getTitle(), e);
            }
        } while (option != menu.getExit());
    }

    /**
     * Outputs a message whenever an error occurs while executing a menu option
     * @param option the option executed
     * @param title the title of the menu
     * @param e the Exception thrown
     */
    public static void printExceptionOnMenu(short option, String title, Exception e) {
        System.out.printf("Error performing option %d of menu \"%s\":\n\t%s\n", option, title, e.getMessage());
    }
}
