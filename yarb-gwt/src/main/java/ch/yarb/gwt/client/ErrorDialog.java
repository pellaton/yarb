package ch.yarb.gwt.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;

public class ErrorDialog extends DialogBox {

  public ErrorDialog(String error) {
    setText(error);

    // Enable animation.
    setAnimationEnabled(true);

    // Enable glass background.
    setGlassEnabled(true);

    // DialogBox is a SimplePanel, so you have to set its widget property to
    // whatever you want its contents to be.
    Button ok = new Button("OK");
    ok.addClickHandler(new ClickHandler() {

      public void onClick(ClickEvent event) {
        ErrorDialog.this.hide();
      }

    });
    setWidget(ok);
  }
}
