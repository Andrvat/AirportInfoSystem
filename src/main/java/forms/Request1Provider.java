package forms;

import controller.ControllerManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Request1Provider extends AbstractRequestProvider {
    private static final String DESCRIPTION = "<html>Получить список и общее число всех pаботников аэpопоpта, " +
            "начальников отделов, pаботников указанного отдела, по стажу pаботы в аэpопоpту, " +
            "половому пpизнаку, возpасту, пpизнаку наличия и количеству детей, " +
            "по pазмеpу заpаботной платы</html>";

    private final Map<String, String[]> options = new HashMap<>() {{
        put("Специальность", new String[]{"="});
        put("Отдел", new String[]{"="});
        put("Стаж работы", new String[]{"<", ">", "="});
        put("Возраст", new String[]{"<", ">", "="});
        put("Пол", new String[]{"="});
        put("Наличие детей", new String[]{"="});
        put("Количество детей", new String[]{"<", ">", "="});
        put("Размер заработной платы", new String[]{"<", ">", "="});
    }};

    public Request1Provider() {
        super(DESCRIPTION);
    }

    @Override
    public void performRequest(ControllerManager controllerManager) {

    }

    @Override
    public FormPackage getPreparedFromPackage() {
        FormPackage formPackage = new FormPackage();
        formPackage.setDescription(this.getDescription());
        formPackage.setLabelTexts(this.options.keySet().toArray(new String[0]));
        formPackage.setOptions(new ArrayList<>(this.options.values()));
        return formPackage;
    }
}
