package cz.uhk.tj_start_rk.model.json_view;

public class View {
    public static class Base {}

    public static class BasicMember extends Base{}

    public static class AllEvent extends Base{}
    public static class AllTeam extends Base{}
    public static class AllTraining extends Base{}
    public static class AllMember extends BasicMember{}
    public static class AllMatch extends Base{}

    public static class AllMemberWithPassword extends AllMember{}
}