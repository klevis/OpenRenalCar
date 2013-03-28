package ramo.klevis.openrental.utils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.swt.widgets.Display;

public class ModelServiceExampleHandler {

  @Execute
  public void execute(MApplication application, 
      EModelService service,
      Display display) {
    System.out.println("Got Model Service: " 
      + (service != null));

    // Alternatively to get the model service 
    // via the application
    EModelService modelService = 
        (EModelService) application.getContext()
        .get(EModelService.class.getName());

    // both services are identical
    System.out.println("Got Model Service: " 
        + (service != modelService));

    // Find objects by ID
    findObjectsById(application, service);

    // Find objects by type
    findObjectsByType(application, service);

    // Find objects by tags
    findObjectsByTag(application, service);

    // Get the MWindow and change its size
    getWindowAndChangeSize(application, service, display);

  }
  
  

  // Example for search by ID
  private void findObjectsById(MApplication application, 
      EModelService service) {
    List<MPart> findElements = service.findElements(application, 
        "mypart",MPart.class, null);
    System.out.println("Found part(s) : " + findElements.size());

  }

  // Example for search by Type
  private void findObjectsByType(MApplication application,
      EModelService service) {
    List<MPart> parts = service.findElements(application, null,
        MPart.class, null);
    System.out.println("Found parts(s) : " + parts.size());

  }

  // Example for search by Tag
  private void findObjectsByTag(MApplication application,
      EModelService service) {
    List<String> tags = new ArrayList<String>();
    tags.add("justatag");
    List<MUIElement> elementsWithTags = service.findElements(application,
        null, null, tags);
    System.out.println("Found parts(s) : " + elementsWithTags.size());
  }

  // Example: Get the MWindow and change its size
    private void getWindowAndChangeSize(MApplication application,
        EModelService service, Display display) {
      List<MWindow> windows = service.findElements(application, null,
          MWindow.class, null);
      if (windows.size() >= 1) {
        MWindow mWindow = windows.get(0);
        System.out.println("Got the window");
        for (int i = mWindow.getWidth(); 
            i >= mWindow.getWidth() - 100; i--) {
          while (!display.readAndDispatch()) {
            mWindow.setWidth(i);
            wait10();
          }
        }
      }

    }
  
  private void wait10() {
    try {
      TimeUnit.SECONDS.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
} 