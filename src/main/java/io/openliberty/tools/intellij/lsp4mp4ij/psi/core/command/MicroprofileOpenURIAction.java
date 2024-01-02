package io.openliberty.tools.intellij.lsp4mp4ij.psi.core.command;

import com.google.gson.JsonPrimitive;
import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.microshed.lsp4ij.operations.codelens.LSPInlayProvider;

import java.util.List;

public class MicroprofileOpenURIAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        String url = getURL(e);
        if (url != null) {
            BrowserUtil.browse(url);
        }
    }

    private String getURL(AnActionEvent e) {
        String url = null;
        List<Object> arguments = e.getData(LSPInlayProvider.LSP_COMMAND).getArguments();
        if (!arguments.isEmpty()) {
            Object arg = arguments.get(0);
            if (arg instanceof JsonPrimitive) {
                url = ((JsonPrimitive) arg).getAsString();
            } else if (arg instanceof String) {
                url = (String) arg;
            }
        }
        return url;
    }
}
