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

import com.igornunes.intf.NavigationItem;
import com.igornunes.intf.Thunk;

/**
 * @author Igor Nunes
 */
public class MenuItem implements NavigationItem {
    private String prompt;
    private Thunk procedure;

    /**
     * @return the prompt
     */
    public String getPrompt() {
        return this.prompt;
    }

    /**
     * @param prompt the prompt to set
     * @return the instance itself
     */
    public MenuItem setPrompt(String prompt) {
        this.prompt = prompt;
        return this;
    }

    /**
     * @return the procedure
     */
    public Thunk getProcedure() {
        return this.procedure;
    }

    /**
     * @param procedure the procedure to set
     * @return the instance itself
     */
    public MenuItem setProcedure(Thunk procedure) {
        this.procedure = procedure;
        return this;
    }

    /**
     * @return the string representation of the instance
     */
    @Override
    public String toString() {
        return this.getPrompt();
    }

    /**
     * @param prompt the initial prompt to set
     * @param procedure the initial procedure to set
     */
    public MenuItem(String prompt, Thunk procedure) {
        this.procedure = procedure;
        this.prompt = prompt;
    }
}
