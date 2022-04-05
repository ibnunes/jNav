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

package com.igornunes.intf;

import java.util.ArrayList;

/**
 * @author Igor Nunes
 */
public interface Navigation<T extends NavigationItem> {
    String getTitle();
    void setTitle(String title);
    ArrayList<T> getItems();
    short getExit();
    boolean hasExit();
    void setItems(ArrayList<T> items);
    Navigation addItem(T item, boolean isExit);
    void show();
    short getOption(final String PROMPT, final String NOOPTION, final String ERRMSG);
    void execute(short option) throws Exception;
}
