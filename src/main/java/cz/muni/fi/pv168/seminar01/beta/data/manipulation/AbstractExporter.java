package cz.muni.fi.pv168.seminar01.beta.data.manipulation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

public abstract class AbstractExporter<T> {
    protected static final String SEPARATOR = ";";

    public void export(Collection<T> data, String filePath) {
        try (var writer = new BufferedWriter(new FileWriter(filePath, StandardCharsets.UTF_8))) {
            for (var element : data) {
                String line = createCsvLine(element);
                writer.write(line);
                writer.newLine();
            }
            writeAfterMain(writer);
        } catch (IOException exception) {
            throw new DataManipulationException("Unable to write to file", exception);
        }
    }

    protected abstract String createCsvLine(T element);

    /**
     * Will be used for importing categories later
     *
     * @param writer is a BufferedWriter with specific file
     */
    protected abstract void writeAfterMain(BufferedWriter writer);
}
