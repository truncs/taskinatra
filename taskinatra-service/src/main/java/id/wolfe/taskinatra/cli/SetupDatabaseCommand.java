package id.wolfe.taskinatra.cli;

import com.yammer.dropwizard.AbstractService;
import com.yammer.dropwizard.cli.ConfiguredCommand;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.Database;
import com.yammer.dropwizard.db.DatabaseFactory;
import com.yammer.dropwizard.logging.Log;
import id.wolfe.taskinatra.TaskinatraServiceConfiguration;
import id.wolfe.taskinatra.db.PeopleDAO;
import org.apache.commons.cli.CommandLine;

/**
 * Setup the database creating tables and other objects.
 */
public class SetupDatabaseCommand extends ConfiguredCommand<TaskinatraServiceConfiguration> {

    public SetupDatabaseCommand() {
        super("setup", "Setup the database.");
    }

    @Override
    protected void run(AbstractService<TaskinatraServiceConfiguration> service, TaskinatraServiceConfiguration configuration, CommandLine params) throws Exception {

        final Log log = Log.forClass(SetupDatabaseCommand.class);
        final Environment environment = new Environment(configuration, service);

        final DatabaseFactory factory = new DatabaseFactory(environment);
        final Database db = factory.build(configuration.getDatabaseConfiguration(), "h2");
        final PeopleDAO peopleDAO = db.onDemand(PeopleDAO.class);

        log.info("creating tables.");
        peopleDAO.createPeopleTable();

    }
}
