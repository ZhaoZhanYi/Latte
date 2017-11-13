package latte.demo.latte.compiler;

import com.google.auto.service.AutoService;

import org.demo.latte.annotation.AppRegisterGenerator;
import org.demo.latte.annotation.EntryGenerator;
import org.demo.latte.annotation.PayEntryGenerator;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 * Created by zhanyi on 2017/10/29.
 */

@AutoService(Processor.class)
public class LatteProcessor extends AbstractProcessor {


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        final Set<String> types = new LinkedHashSet<>();
        final Set<Class<? extends Annotation>> supportAnnotations = getSupportedAnnotations();
        for (Class<? extends Annotation> annotation : supportAnnotations) {
            types.add(annotation.getCanonicalName());
            System.out.printf("======" + annotation.getCanonicalName() + "\n");
        }
        return types;
    }

    private Set<Class<? extends Annotation>> getSupportedAnnotations() {
        final Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
        annotations.add(EntryGenerator.class);
        annotations.add(PayEntryGenerator.class);
        annotations.add(AppRegisterGenerator.class);
        return annotations;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        for (TypeElement a : set) {
            System.out.printf("===scan1===" + a.getSimpleName() + "\n");
        }

        generateEntryCode(roundEnvironment);
        generatePayEntryCode(roundEnvironment);
        generateAppRegisterCode(roundEnvironment);
        return true;

    }

    private void scan(RoundEnvironment env, Class<? extends Annotation> annotation, AnnotationValueVisitor visitor) {
        //遍历给定注解类型的元素，
        System.out.printf("===scan===" + annotation.getCanonicalName() + "\n");
        for (Element typeElement : env.getElementsAnnotatedWith(annotation)) {
            System.out.printf("===scan1===" + typeElement.getSimpleName() + "\n");

            //获得此元素上的所有注解
            final List<? extends AnnotationMirror> annotationMirrors = typeElement.getAnnotationMirrors();
            //遍历每一个注解，注解包括注解的类型和注解元素的值
            for (AnnotationMirror annotationMirror : annotationMirrors) {
                System.out.printf("===scan2===" + annotationMirror.toString() + "\n");

                //获取注解元素的值
                final Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues
                        = annotationMirror.getElementValues();

                //
                for (Map.Entry<? extends  ExecutableElement, ? extends AnnotationValue>
                        entry : elementValues.entrySet() ) {
                    System.out.printf("===scan3===" + entry.getValue().toString() + "\n");

                    entry.getValue().accept(visitor, null);
                }
            }
        }

    }

    private void generateEntryCode(RoundEnvironment env) {
        EntryVistor entryVistor = new EntryVistor();
        entryVistor.setFiler(processingEnv.getFiler());
        scan(env, EntryGenerator.class, entryVistor);
    }

    private void generatePayEntryCode(RoundEnvironment env) {
        PayEntryVistor entryVistor = new PayEntryVistor();
        entryVistor.setFiler(processingEnv.getFiler());
        scan(env, PayEntryGenerator.class, entryVistor);
    }

    private void generateAppRegisterCode(RoundEnvironment env) {
        AppRegisterVistor entryVistor = new AppRegisterVistor();
        entryVistor.setFiler(processingEnv.getFiler());
        scan(env, AppRegisterGenerator.class, entryVistor);
    }


}
