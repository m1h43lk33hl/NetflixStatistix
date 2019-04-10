package com.company.applicationlogic;

/**
 * Enum ErrorMessages handles ErrorMessages  
 */
public enum ErrorMessages {
        ACCOUNT_NOT_SELECTED(0, "Er is geen account geselecteerd!"),
        PROFILE_NOT_SELECTED(1, "Er is geen profiel geselecteerd"),
        ACCOUNT_EXISTS(2, "Account bestaat al!"),
        ROW_NOT_SELECTED(3, "Er is geen rij in de tabel geselecteerd!"),
        TABLE_HAS_DUPLICATES(4, "Iedere rij moet een unieke film of aflevering bevatten!"),
        FILTER_NOT_NUMERICAL(5, "Filter waarde moet een numerieke waarde bevatten!"),
        ACCOUNT_NOT_VALID(6, "Er is geen valide account geselecteerd!"),
        ACCOUNT_DATA_NOT_VALID(7, "Account bestaat al of geen juiste waarden ingevoerd!"),
        LOG_DATA_NOT_VALID(8, "Log data bestaat al of geen juiste waarden ingevoerd!"),
        PROFILE_DATA_NOT_VALID(9, "Log data bestaat al of geen juiste waarden ingevoerd!");


        private final int code;
        private final String description;

    /**
     * Enum constructor for enum ErrorMessages
     *
     * @param code
     * @param description
     */
    private ErrorMessages(int code, String description) {
            this.code = code;
            this.description = description;
        }

    /**
     * Returns description
     *
     * @return
     */
    public String getDescription() {
            return description;
        }

    /**
     * Returns code
     *
     * @return
     */
    public int getCode() {
            return code;
        }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
        public String toString() {
            return code + ": " + description;
        }
    }
