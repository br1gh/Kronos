/*
 * This file is part of kronos.
 *
 * kronos is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * kronos is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with kronos.  If not, see <https://www.gnu.org/licenses/>.
 *
 * Licensed under the GNU GPL v3 License
 * SPDX-License-Identifier: GPL-3.0-only
 */


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class JobTest
{
    @Test
    @DisplayName("dateAllNull")
    public void dateAllNull()
    {
        Job j = new Job(0, "", null, null, null, null, null);
        assertTrue(j.doesDateSatisfy(LocalDateTime.now()));
    }

    @Test
    @DisplayName("dateExact")
    public void dateExact()
    {
        Job j = new Job(0, "", 1, 1, 6, 1, 1);
        assertTrue(j.doesDateSatisfy(LocalDateTime.of(2022, 1, 1, 1, 1)));
    }

}
