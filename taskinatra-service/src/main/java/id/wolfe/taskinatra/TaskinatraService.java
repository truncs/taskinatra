package id.wolfe.taskinatra;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.bundles.AssetsBundle;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.Database;
import com.yammer.dropwizard.db.DatabaseFactory;
import id.wolfe.taskinatra.cli.SetupDatabaseCommand;
import id.wolfe.taskinatra.db.PeopleDAO;
import id.wolfe.taskinatra.resources.PeopleResource;
import id.wolfe.taskinatra.resources.PersonResource;

/**
 *
 */
public class TaskinatraService extends Service<TaskinatraServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new TaskinatraService().run(args);
    }

    public TaskinatraService() {
        super("taskinatra");
        addCommand(new SetupDatabaseCommand());
        addBundle(new AssetsBundle());
    }

    @Override
    protected void initialize(TaskinatraServiceConfiguration taskinatraServiceConfiguration, Environment environment) throws Exception {

        final DatabaseFactory factory = new DatabaseFactory(environment);
        final Database db = factory.build(taskinatraServiceConfiguration.getDatabaseConfiguration(), "h2");
        final PeopleDAO peopleDAO = db.onDemand(PeopleDAO.class);

        environment.addResource(new PeopleResource(peopleDAO));
        environment.addResource(new PersonResource(peopleDAO));
    }
}
