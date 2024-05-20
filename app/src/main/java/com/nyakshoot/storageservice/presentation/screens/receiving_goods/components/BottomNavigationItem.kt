package com.nyakshoot.storageservice.presentation.screens.receiving_goods.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavigationItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: @Composable() (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    selectedContentColor: Color = MaterialTheme.colors.primary,
    unselectedContentColor: Color = contentColorFor(backgroundColor = MaterialTheme.colors.background),
    selectedBorderColor: Color = Color(0x1AD9D9D9),
    borderWidth: Dp = 2.dp
) {
    val selectionAnimationDuration = 90
    val selectionAnimation = updateTransition(
        selected,
        label = "BottomNavigationItem Animation"
    )

    val selectedIconColor by selectionAnimation.animateColor(
        transitionSpec = {
            val isReversed = !targetState != !initialState
            if (isReversed) {
                tween(durationMillis = selectionAnimationDuration)
            } else {
                snap()
            }
        },
        label = "BottomNavigationItem Icon Color"
    ) { isSelected ->
        if (isSelected) selectedContentColor else unselectedContentColor
    }

    Box(
        modifier = modifier
            .clip(CircleShape)
            .border(
                width = if (selected) borderWidth else 0.dp,
                color = selectedBorderColor,
                shape = CircleShape
            )
            .clickable(
                onClick = onClick,
                enabled = enabled,
                role = Role.Tab,
                interactionSource = interactionSource,
                indication = null
            )
            .padding(8.dp)
    ) {
        CompositionLocalProvider(
            LocalContentColor provides selectedIconColor
        ) {
            icon()
        }

        AnimatedVisibility(
            visible = selected || alwaysShowLabel,
            enter = slideInVertically { it },
            exit = slideOutVertically { it }
        ) {
            label?.invoke()
        }
    }
}