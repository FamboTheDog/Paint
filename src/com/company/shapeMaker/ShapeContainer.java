package com.company.shapeMaker;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Data public class ShapeContainer {
    Color color;
    Shape shape;
}
