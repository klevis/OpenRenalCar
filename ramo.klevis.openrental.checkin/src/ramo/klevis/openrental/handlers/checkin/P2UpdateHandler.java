package ramo.klevis.openrental.handlers.checkin;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.equinox.p2.core.IProvisioningAgent;
import org.eclipse.equinox.p2.engine.IProvisioningPlan;
import org.eclipse.equinox.p2.operations.ProvisioningJob;
import org.eclipse.equinox.p2.operations.ProvisioningSession;
import org.eclipse.equinox.p2.operations.UpdateOperation;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class P2UpdateHandler {
	// Local repository location (replace with
	// http://www.examplecompany.com/repository)
	private static final String REPOSITORY_LOC = "http://download.eclipse.org/egit/updates-2.3";

	Boolean doInstall = false;

	@Execute
	public void execute(final IProvisioningAgent agent, final Shell parent,
			final UISynchronize sync) {

		doInstall = false;
		Job j = new Job("Update Job") {

			@Override
			protected IStatus run(final IProgressMonitor monitor) {

				/* 1. Prepare update plumbing */

				
				final ProvisioningSession session = new ProvisioningSession(
						agent);
				final UpdateOperation operation = new UpdateOperation(session);

				// Create uri
				URI uri = null;
				try {
					uri = new URI(REPOSITORY_LOC);
				} catch (URISyntaxException e) {
					return Status.CANCEL_STATUS;
				}

				// Set location of artifact and metadata repo
				operation.getProvisioningContext().setArtifactRepositories(
						new URI[] { uri });
				operation.getProvisioningContext().setMetadataRepositories(
						new URI[] { uri });

				/* 2. Check for updates */

				// Run update checks causing I/O
				final IStatus status = operation.resolveModal(monitor);

				// Failed to find updates (inform user and exit)
				if (status.getCode() == UpdateOperation.STATUS_NOTHING_TO_UPDATE) {
					sync.syncExec(new Runnable() {
						@Override
						public void run() {
							MessageDialog
									.openWarning(parent, "No update",
											"No updates for the current installation have been found");
						}
					});
					return Status.CANCEL_STATUS;
				}

				/* 3. Ask if updates should be installed and run installation */

				// found updates, ask user if to install?
				if (status.isOK() && status.getSeverity() != IStatus.ERROR) {
					sync.syncExec(new Runnable() {
						@Override
						public void run() {

							doInstall = MessageDialog.openQuestion(parent,
									"Relly Install?", status.getMessage());

						}
					});
				}

				// Install! (causing I/0!)
				if (doInstall.booleanValue()) {
					
					
					
					
					IProvisioningPlan provisioningPlan = operation.getProvisioningPlan();
					System.err.println("Okiiiiiiiiii "+provisioningPlan);

					ProvisioningJob resolveJob = operation.getResolveJob(monitor);
					
					resolveJob.schedule();
					ProvisioningJob provisioningJob = operation.getProvisioningJob(monitor);
					
						provisioningJob.schedule();
					
					//					System.err.println(monitor);
//					operation.getProvisioningJob(monitor).schedule();
					// Optionally register a job change listener to track
					// installation progress and notify user upon success
					
					
					
				}
				return Status.OK_STATUS;
			}
		};
		j.schedule();
	}
}