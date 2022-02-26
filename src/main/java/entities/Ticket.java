package entities;

import annotations.DbColumnNumber;
import annotations.DbColumnVarchar;
import annotations.DbConstrains;
import annotations.DbTable;

@DbTable(name = "TICKET")
public class Ticket extends AbstractComponent {
    @DbColumnNumber(name = "ID_TICKET", constrains = @DbConstrains(isPrimaryKey = true))
    private Integer id;

    @DbColumnVarchar(name = "FIRST_NAME", value = 50, constrains = @DbConstrains(isAllowedNull = false))
    private String firstName;

    @DbColumnVarchar(name = "LAST_NAME", value = 50, constrains = @DbConstrains(isAllowedNull = false))
    private String lastName;

    @DbColumnVarchar(name = "PATRONYMIC", value = 50, constrains = @DbConstrains(isAllowedNull = false))
    private String patronymic;

    @DbColumnNumber(name = "SEAT_NUMBER", constrains = @DbConstrains(isAllowedNull = false))
    private Integer seat;

    public Ticket() {
        super(Ticket.class.getAnnotation(DbTable.class).name());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }
}
