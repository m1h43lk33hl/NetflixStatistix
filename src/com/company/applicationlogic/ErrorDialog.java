package com.company.applicationlogic;

import javax.swing.*;

/**
 * Class ErrorDialog handles error dialogues
 */
public class ErrorDialog {

    /**
     *
     * @param errorMessages
     */
    public static void showErrorDialog(ErrorMessages errorMessages)
    {
        JOptionPane.showMessageDialog(null,
                errorMessages.getDescription(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

}
