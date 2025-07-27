module lecture {
    requires java.base;
    requires jsr305;
    requires java.desktop;
    exports lecture.two.abstrclasses;
    exports lecture.two.annotations;
    exports lecture.two.anonymous;
    exports lecture.two.arrays;
    exports lecture.two.classes;
    exports lecture.two.enums;
    exports lecture.two.interfaces;
    exports lecture.two.lambdas;
    exports lecture.two.local;
    exports lecture.two.methodreferences;
    exports lecture.two.records;
    exports lecture.two.strings;
    opens lecture.two.annotations;
}