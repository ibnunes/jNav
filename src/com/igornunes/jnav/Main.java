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

/**
 * @author Igor Nunes
 */
public class Main {
    private static Menu main;
    private static Menu foo;
    private static Menu bar;

    private static void printText(String text) {
        System.out.println(text);
    }

    private static void startCLI() {
        main = new Menu("Main Menu");
        main.addItem("Foo", () -> Menu.runMenu(foo));
        main.addItem("EXIT", Menu.VOID, true);      // This is the exit option

        foo = new Menu("FOO");
        foo.addItem("Show text", () -> printText("This was inside FOO"));
        foo.addItem("Bar", () -> Menu.runMenu(bar));
        foo.addItem("EXIT", Menu.VOID, true);

        bar = new Menu("BAR");
        bar.addItem("Show text", () -> printText("This was inside BAR"));
        bar.addItem("EXIT", Menu.VOID, true);
    }

    public static void main(String[] args) {
        startCLI();
        Menu.runMenu(main);
        System.out.println("Good bye Maria Ivone :')");
    }
}
