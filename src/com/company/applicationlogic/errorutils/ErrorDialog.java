package com.company.applicationlogic.errorutils;

import javax.swing.*;

/**
 * Class ErrorDialog handles error dialogues
 */
public class ErrorDialog {

    /**
     * Shows a dialog with an error message.
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
