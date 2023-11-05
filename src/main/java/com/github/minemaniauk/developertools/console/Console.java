/*
 * DeveloperTools
 * A library of tools helpful in testing and development.
 *
 * Copyright (C) 2023  MineManiaUK Staff
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.github.minemaniauk.developertools.console;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a utility class to send messages
 * with color to the console.
 */
public class Console {

    private static @Nullable String logPrefix = "&7[&aLOG&7] ";
    private static @Nullable String warnPrefix = "&7[&eWARN&7] ";

    /**
     * Represents a local console logger.
     * Used to configure custom prefixes for logging.
     */
    public class Local {

        private boolean hasGlobalPrefix;
        private @Nullable String localLogPrefix = "";
        private @Nullable String localWarnPrefix = "";

        /**
         * Used to create a new instance of
         * a console logger.
         *
         * @param hasGlobalPrefix True if the global prefix should be used.
         */
        public Local(boolean hasGlobalPrefix) {
            this.hasGlobalPrefix = hasGlobalPrefix;
        }

        public @NotNull Local setLocalLogPrefix(@Nullable String localLogPrefix) {
            this.localLogPrefix = localLogPrefix;
            return this;
        }

        public @NotNull Local setLocalWarnPrefix(@Nullable String localWarnPrefix) {
            this.localWarnPrefix = localWarnPrefix;
            return this;
        }

        public @NotNull Local setBothLocalPrefixes(@Nullable String localPrefix) {
            this.localLogPrefix = localPrefix;
            this.localWarnPrefix = localPrefix;
            return this;
        }

        /**
         * Used to log a message in console
         * using this instance of the local console
         * with the custom prefixes.
         *
         * @param message The message to log.
         * @return This instance.
         */
        public @NotNull Local log(@NotNull String message) {
            String prefix = (this.hasGlobalPrefix ? Console.logPrefix : "")
                    + (this.localLogPrefix == null ? "" : this.localLogPrefix);

            System.out.println(ConsoleColor.parse(
                    prefix + message
            ));
            return this;
        }

        /**
         * Used to log a warning in console
         * using this instance of the local console
         * with the custom prefixes.
         *
         * @param message The message to log.
         * @return This instance.
         */
        public @NotNull Local warn(@NotNull String message) {
            String prefix = (this.hasGlobalPrefix ? Console.warnPrefix : "")
                    + (this.localWarnPrefix == null ? "" : this.localWarnPrefix);

            System.out.println(ConsoleColor.parse(
                    prefix + message
            ));
            return this;
        }

        /**
         * Used to create a new instance of a local console
         * class with an extension to the prefixes.
         *
         * @param extensionPrefix The extension to add.
         * @return The new instance of the local console logger.
         */
        public @NotNull Local createExtension(@Nullable String extensionPrefix) {
            Local clone = this.clone();
            clone.setLocalLogPrefix(clone.localLogPrefix + extensionPrefix);
            clone.setLocalWarnPrefix(clone.localWarnPrefix + extensionPrefix);
            return clone;
        }

        @Override
        protected @NotNull Local clone() {
            return new Local(this.hasGlobalPrefix)
                    .setLocalLogPrefix(this.localLogPrefix)
                    .setLocalWarnPrefix(this.localWarnPrefix);
        }
    }

    /**
     * Used to log a message in console.
     * This will support replacing color codes.
     *
     * @param message The instance of the message.
     */
    public static void log(@NotNull String message) {
        System.out.println(ConsoleColor.parse(
                (logPrefix == null ? "" : logPrefix) + message
        ));
    }

    /**
     * Used to log a warning in the console.
     * This will support replacing color codes.
     *
     * @param message The instance of the message.
     */
    public static void warn(@NotNull String message) {
        System.out.println(ConsoleColor.parse(
                (warnPrefix == null ? "" : warnPrefix) + message
        ));
    }

    /**
     * Used to set the log prefix.
     * This will support replacing color codes.
     *
     * @param prefix The instance of the prefix.
     */
    public static void setLogPrefix(@Nullable String prefix) {
        Console.logPrefix = prefix;
    }

    /**
     * Used to set the warning prefix.
     * This will support replacing color codes.
     *
     * @param prefix The instance of the prefix.
     */
    public static void setWarnPrefix(@Nullable String prefix) {
        Console.warnPrefix = prefix;
    }

    /**
     * Used to set both the log and warn prefix.
     * This will support replacing color codes.
     *
     * @param prefix The instance of the prefix.
     */
    public static void setBothPrefixes(@Nullable String prefix) {
        Console.logPrefix = prefix;
        Console.warnPrefix = prefix;
    }
}
