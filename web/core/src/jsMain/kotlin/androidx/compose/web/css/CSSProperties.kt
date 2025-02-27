package org.jetbrains.compose.web.css

fun StyleBuilder.opacity(value: Number) {
    property("opacity", value)
}

fun StyleBuilder.order(value: Int) {
    property("order", value)
}

fun StyleBuilder.flexGrow(value: Number) {
    property("flex-grow", value)
}

fun StyleBuilder.flexShrink(value: Number) {
    property("flex-shrink", value)
}

fun StyleBuilder.opacity(value: CSSSizeValue<CSSUnit.percent>) {
    property("opacity", (value.value as Double / 100))
}

fun StyleBuilder.color(value: String) {
    property("color", value)
}

fun StyleBuilder.color(value: Color) {
    // color hasn't Typed OM yet
    property("color", value.styleValue())
}

fun StyleBuilder.backgroundColor(value: CSSVariableValue<Color>) {
    property("background-color", value)
}

fun StyleBuilder.backgroundColor(value: Color) {
    property("background-color", value.styleValue())
}

fun StyleBuilder.backgroundColor(value: String) {
    property("background-color", value)
}

enum class LineStyle {
    None,
    Hidden,
    Dotted,
    Dashed,
    Solid,
    Double,
    Groove,
    Ridge,
    Inset,
    Outset
}

enum class DisplayStyle(val value: String) {
    Block("block"),
    Inline("inline"),
    InlineBlock("inline-block"),
    Flex("flex"),
    LegacyInlineFlex("inline-flex"),
    Grid("grid"),
    LegacyInlineGrid("inline-grid"),
    FlowRoot("flow-root"),

    None("none"),
    Contents("contents"),

// TODO(shabunc): This properties behave them iconsistenly in both Chrome and Firefox so I turned the off so far
//    BlockFlow("block flow"),
//    InlineFlow("inline flow"),
//    InlineFlowRoot("inline flow-root"),
//    BlocklFlex("block flex"),
//    InlineFlex("inline flex"),
//    BlockGrid("block grid"),
//    InlineGrid("inline grid"),
//    BlockFlowRoot("block flow-root"),

    Table("table"),
    TableRow("table-row"),
    ListItem("list-item"),

    Inherit("inherit"),
    Initial("initial"),
    Unset("unset")
}

enum class FlexDirection(val value: String) {
    Row("row"),
    RowReverse("row-reverse"),
    Column("column"),
    ColumnReverse("column-reverse")
}

enum class FlexWrap(val value: String) {
    Wrap("wrap"),
    Nowrap("nowrap"),
    WrapReverse("wrap-reverse")
}

enum class JustifyContent(val value: String) {
    Center("center"),
    Start("start"),
    End("end"),
    FlexStart("flex-start"),
    FlexEnd("flex-end"),
    Left("left"),
    Right("right"),
    Normal("normal"),
    SpaceBetween("space-between"),
    SpaceAround("space-around"),
    SpaceEvenly("space-evenly"),
    Stretch("stretch"),
    Inherit("inherit"),
    Initial("initial"),
    Unset("unset"),
    SafeCenter("safe center"),
    UnsafeCenter("unsafe center"),
}

enum class AlignSelf(val value: String) {
    Auto("auto"),
    Normal("normal"),
    Center("center"),
    Start("start"),
    End("end"),
    SelfStart("self-start"),
    SelfEnd("self-end"),
    FlexStart("flex-start"),
    FlexEnd("flex-end"),
    Baseline("baseline"),
//    FirstBaseline("first baseline"),
//    LastBaseline("last baseline"),
    Stretch("stretch"),
    SafeCenter("safe center"),
    UnsafeCenter("unsafe center"),
    Inherit("inherit"),
    Initial("initial"),
    Unset("unset")
}

enum class AlignItems(val value: String) {
    Normal("normal"),
    Stretch("stretch"),
    Center("center"),
    Start("start"),
    End("end"),
    FlexStart("flex-start"),
    FlexEnd("flex-end"),
    Baseline("baseline"),
//    FirstBaseline("first baseline"),
//    LastBaseline("last baseline"),
    SafeCenter("safe center"),
    UnsafeCenter("unsafe center"),

    Inherit("inherit"),
    Initial("initial"),
    Unset("unset")
}

enum class AlignContent(val value: String) {
    Center("center"),
    Start("start"),
    End("end"),
    FlexStart("flex-start"),
    FlexEnd("flex-end"),
    Baseline("baseline"),
//    FirstBaseline("first baseline"),
//    LastBaseline("last baseline"),
    SafeCenter("safe center"),
    UnsafeCenter("unsafe center"),
    SpaceBetween("space-between"),
    SpaceAround("space-around"),
    SpaceEvenly("space-evenly"),
    Stretch("stretch"),

    Inherit("inherit"),
    Initial("initial"),
    Unset("unset")
}

enum class Position(val value: String) {
    Static("static"),
    Relative("relative"),
    Absolute("absolute"),
    Sticky("sticky"),
    Fixed("fixed")
}

class CSSBorder : CustomStyleValue {
    var width: CSSUnitValue? = null
    var style: StylePropertyValue? = null
    var color: StylePropertyValue? = null

    fun width(size: CSSUnitValue) {
        width = size
    }

    fun style(style: LineStyle) {
        this.style = style.name.unsafeCast<StylePropertyValue>()
    }

    fun color(color: Color) {
        this.color = color.styleValue()
    }

    fun color(color: CSSVariableValue<Color>) {
        this.color = color
    }

    override fun equals(other: Any?): Boolean {
        return if (other is CSSBorder) {
            styleValue().toString() == other.styleValue().toString()
        } else false
    }

    override fun styleValue(): StylePropertyValue {
        val values = listOfNotNull(width?.asString(), style, color)
        return values.joinToString(" ").unsafeCast<StylePropertyValue>()
    }
}

inline fun StyleBuilder.border(crossinline borderBuild: CSSBorder.() -> Unit) {
    val border = CSSBorder().apply(borderBuild)
    property("border", border.styleValue())
}

fun StyleBuilder.border(
    width: CSSLengthValue? = null,
    style: LineStyle? = null,
    color: Color? = null
) {
    border {
        width?.let { width(it) }
        style?.let { style(it) }
        color?.let { color(it) }
    }
}

fun StyleBuilder.display(displayStyle: DisplayStyle) {
    property("display", displayStyle.value)
}

fun StyleBuilder.flexDirection(flexDirection: FlexDirection) {
    property("flex-direction", flexDirection.value)
}

fun StyleBuilder.flexWrap(flexWrap: FlexWrap) {
    property("flex-wrap", flexWrap.value)
}

fun StyleBuilder.flexFlow(flexDirection: FlexDirection, flexWrap: FlexWrap) {
    property(
        "flex-flow",
        "${flexDirection.value} ${flexWrap.value}"
    )
}

fun StyleBuilder.justifyContent(justifyContent: JustifyContent) {
    property(
        "justify-content",
        justifyContent.value
    )
}
fun StyleBuilder.alignSelf(alignSelf: AlignSelf) {
    property(
        "align-self",
        alignSelf.value
    )
}

fun StyleBuilder.alignItems(alignItems: AlignItems) {
    property(
        "align-items",
        alignItems.value
    )
}

fun StyleBuilder.alignContent(alignContent: AlignContent) {
    property(
        "align-content",
        alignContent.value
    )
}

fun StyleBuilder.position(position: Position) {
    property(
        "position",
        position.value
    )
}

fun StyleBuilder.borderRadius(r: CSSUnitValue) {
    property("border-radius", r)
}

fun StyleBuilder.borderRadius(topLeft: CSSUnitValue, bottomRight: CSSUnitValue) {
    property("border-radius", "${topLeft.asString()} ${bottomRight.asString()}")
}

fun StyleBuilder.borderRadius(
    topLeft: CSSUnitValue,
    topRightAndBottomLeft: CSSUnitValue,
    bottomRight: CSSUnitValue
) {
    property("border-radius", "${topLeft.asString()} ${topRightAndBottomLeft.asString()} ${bottomRight.asString()}")
}

fun StyleBuilder.borderRadius(
    topLeft: CSSUnitValue,
    topRight: CSSUnitValue,
    bottomRight: CSSUnitValue,
    bottomLeft: CSSUnitValue
) {
    property(
        "border-radius",
        "${topLeft.asString()} ${topRight.asString()} ${bottomRight.asString()} ${bottomLeft.asString()}"
    )
}

fun StyleBuilder.width(value: CSSUnitValue) {
    property("width", value)
}

fun StyleBuilder.width(value: CSSAutoValue) {
    property("width", value)
}

fun StyleBuilder.height(value: CSSUnitValue) {
    property("height", value)
}

fun StyleBuilder.height(value: CSSAutoValue) {
    property("height", value)
}

fun StyleBuilder.top(value: CSSLengthOrPercentageValue) {
    property("top", value)
}

fun StyleBuilder.top(value: CSSAutoValue) {
    property("top", value)
}

fun StyleBuilder.bottom(value: CSSLengthOrPercentageValue) {
    property("bottom", value)
}

fun StyleBuilder.bottom(value: CSSAutoValue) {
    property("bottom", value)
}

fun StyleBuilder.left(value: CSSLengthOrPercentageValue) {
    property("left", value)
}

fun StyleBuilder.left(value: CSSAutoValue) {
    property("left", value)
}

fun StyleBuilder.right(value: CSSLengthOrPercentageValue) {
    property("right", value)
}

fun StyleBuilder.right(value: CSSAutoValue) {
    property("right", value)
}

fun StyleBuilder.fontSize(value: CSSUnitValue) {
    property("font-size", value)
}

fun StyleBuilder.margin(value: CSSUnitValue) {
    // marign hasn't Typed OM yet
    property("margin", value)
}

fun StyleBuilder.marginLeft(value: CSSUnitValue) {
    property("margin-left", value)
}

fun StyleBuilder.marginTop(value: CSSUnitValue) {
    property("margin-top", value)
}

fun StyleBuilder.padding(value: CSSUnitValue) {
    // padding hasn't Typed OM yet
    property("padding", value)
}
