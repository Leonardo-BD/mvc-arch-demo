package com.arch.tests.mvc.arch;

import com.tngtech.archunit.core.domain.*;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import com.tngtech.archunit.library.Architectures;

import java.util.List;
import java.util.stream.Collectors;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.arch.tests.mvc")
public class ArchitectureRulesTest {

    @ArchTest
    static final ArchRule layered =
            Architectures.layeredArchitecture()
                    .consideringOnlyDependenciesInAnyPackage("com.arch.tests.mvc..")
                    .layer("Controller").definedBy("..controller..")
                    .layer("Handler").definedBy("..handler..")
                    .layer("Flux").definedBy("..flux..")
                    .layer("Service").definedBy("..service..")
                    .layer("Repository").definedBy("..repository..")
                    .layer("Validator").definedBy("..validator..")
                    .layer("Dto").definedBy("..dto..")
                    .layer("Entity").definedBy("..entity..")

                    .whereLayer("Controller").mayOnlyBeAccessedByLayers("Handler")
                    .whereLayer("Flux").mayOnlyBeAccessedByLayers("Controller")
                    .whereLayer("Service").mayOnlyBeAccessedByLayers("Flux")
                    .whereLayer("Validator").mayOnlyBeAccessedByLayers("Service", "Flux")
                    .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service", "Validator")
                    .whereLayer("Entity").mayOnlyBeAccessedByLayers("Entity", "Service", "Validator", "Flux", "Repository")
                    .whereLayer("Dto").mayOnlyBeAccessedByLayers("Dto", "Validator", "Flux", "Controller", "Handler");

    @ArchTest
    static final ArchRule service_classes_should_not_access_other_services =
            noClasses().that().resideInAPackage("..service..")
                    .and().haveSimpleNameNotEndingWith("Test")
                    .should().dependOnClassesThat().resideInAPackage("..service..")
                    .because("Service should not access another Service");

    @ArchTest
    static final ArchRule flux_classes_should_not_access_other_flux_classes =
            noClasses().that().resideInAPackage("..flux..")
                    .and().haveSimpleNameNotEndingWith("Test")
                    .should().dependOnClassesThat().resideInAPackage("..flux..")
                    .because("Flux should not access another Flux");

    @ArchTest
    static final ArchRule validator_classes_should_not_access_other_validator_classes =
            noClasses().that().resideInAPackage("..validator..")
                    .and().haveSimpleNameNotEndingWith("Test")
                    .should().dependOnClassesThat().resideInAPackage("..validator..")
                    .because("Validator should not access another Validator");

    @ArchTest
    static final ArchRule controller_classes_should_not_access_other_controller_classes =
            noClasses().that().resideInAPackage("..controller..")
                    .and().haveSimpleNameNotEndingWith("Test")
                    .should().dependOnClassesThat().resideInAPackage("..controller..")
                    .because("Controller should not access another Controller");

    // Flux classes should have only one public method called "execute"
    @ArchTest
    static final ArchRule flux_must_have_only_one_public_method_called_execute =
            classes().that().resideInAPackage("..flux..")
                    .should(new ArchCondition<>("ter somente 1 método público chamado 'execute'") {
                        @Override
                        public void check(JavaClass clazz, ConditionEvents events) {
                            List<JavaMethod> publics = clazz.getMethods().stream()
                                    .filter(m -> m.getModifiers().contains(JavaModifier.PUBLIC))
                                    .toList();

                            boolean ok = publics.size() == 1 && publics.getFirst().getName().equals("execute");
                            if (!ok) {
                                String methods = publics.stream().map(JavaMember::getName).collect(Collectors.joining(", "));
                                String msg = String.format("%s possui %d métodos públicos: [%s] (esperado: apenas 'execute')",
                                        clazz.getName(), publics.size(), methods);
                                events.add(SimpleConditionEvent.violated(clazz, msg));
                            }
                        }
                    });
}
