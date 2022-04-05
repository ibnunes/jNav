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

/**
 * @author Igor Nunes
 */
public interface NavigationItem {
    /**
     * This method must return the prompt set for this navigation item.
     * @return String representation of the prompt.
     */
    String getPrompt();

    /**
     * Sets the prompt text for this navigation item.
     * @param prompt The string representation of the prompt text.
     * @return The instance itself.
     */
    NavigationItem setPrompt(String prompt);

    /**
     * This is the method that returns the procedure to be executed when this item
     * is selected.
     * @return The method to be performed by this navigation item.
     */
    Thunk getProcedure();

    /**
     * Sets the procedure to be performed associated with this navigation item.
     * @param procedure The procedure to be executed.
     * @return The instance itself.
     */
    NavigationItem setProcedure(Thunk procedure);
}
