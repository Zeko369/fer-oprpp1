package hr.fer.zemris.java.fractals.util;

/**
 * Arg parser util used to parse command line arguments.
 *
 * @author franzekan
 */
public class ArgParser {
    private int workers = Runtime.getRuntime().availableProcessors();
    private int tracks = workers * 4;

    private int getValue(String arg) {
        String[] parsed = arg.split("=");
        if (parsed.length != 2) {
            throw new IllegalArgumentException("Arguments should be of a format --argName=argValue");
        }

        return Integer.parseInt(parsed[1]);
    }

    /**
     * Instantiates a new Arg parser.
     *
     * @param args the args
     */
    public ArgParser(String[] args) {
        if (args.length == 0) {
            return;
        }

        for (String arg : args) {
            if (arg.startsWith("--workers") || arg.startsWith("-w")) {
                this.workers = this.getValue(arg);
            } else if (arg.startsWith("--tracks") || arg.startsWith("-t")) {
                this.tracks = this.getValue(arg);
            }
        }
    }

    /**
     * Gets workers.
     *
     * @return the workers
     */
    public int getWorkers() {
        return this.workers;
    }

    /**
     * Gets tracks.
     *
     * @return the tracks
     */
    public int getTracks() {
        return this.tracks;
    }
}
