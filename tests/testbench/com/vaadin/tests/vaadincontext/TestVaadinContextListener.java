/* 
@VaadinApache2LicenseForJavaFiles@
 */

package com.vaadin.tests.vaadincontext;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

import com.vaadin.terminal.gwt.server.BootstrapFragmentResponse;
import com.vaadin.terminal.gwt.server.BootstrapListener;
import com.vaadin.terminal.gwt.server.BootstrapPageResponse;
import com.vaadin.terminal.gwt.server.BootstrapResponse;
import com.vaadin.terminal.gwt.server.VaadinContextEvent;
import com.vaadin.terminal.gwt.server.VaadinContextListener;
import com.vaadin.ui.Root;

public class TestVaadinContextListener implements VaadinContextListener {
    @Override
    public void contextCreated(VaadinContextEvent event) {
        event.getVaadinContext().addBootstrapListener(new BootstrapListener() {
            @Override
            public void modifyBootstrapFragment(
                    BootstrapFragmentResponse response) {
                if (shouldModify(response)) {
                    Element heading = new Element(Tag.valueOf("div"), "")
                            .text("Added by modifyBootstrapFragment");
                    response.getFragmentNodes().add(0, heading);
                }
            }

            private boolean shouldModify(BootstrapResponse response) {
                Root root = response.getRoot();
                boolean shouldModify = root != null
                        && root.getClass() == BoostrapModifyRoot.class;
                return shouldModify;
            }

            @Override
            public void modifyBootstrapPage(BootstrapPageResponse response) {
                if (shouldModify(response)) {
                    response.getDocument().body().child(0)
                            .before("<div>Added by modifyBootstrapPage</div>");
                }
            }
        });
    }

    @Override
    public void contextDestoryed(VaadinContextEvent event) {
        // Nothing to do
    }

}
