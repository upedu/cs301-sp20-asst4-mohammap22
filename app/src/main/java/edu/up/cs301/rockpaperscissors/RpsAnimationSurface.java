package edu.up.cs301.rockpaperscissors;

import android.content.Context;
import android.util.AttributeSet;

import edu.up.cs301.animation.AnimationSurface;
import edu.up.cs301.animation.Animator;

/**
 * Created by vegdahl on 8/1/16.
 */
public class RpsAnimationSurface extends AnimationSurface {	/**
 * Constructor for the AnimationSurface class. In order to be useful, an
 * object must be supplied that implements the Animator interface. This
 * can either be done by overriding the 'createAnimator' method (which by
 * default give null, or by invoking the setAnimator method.
 *
 * @param context
 *            - a reference to the activity this animation is run under
 */
public RpsAnimationSurface(Context context) {
    super(context);
}// ctor

    /**
     * An alternate constructor for use when a subclass is directly specified
     * in the layout. It is expected that the subclass will have overridden
     * the 'createAnimator' method.
     *
     * @param context
     *            - a reference to the activity this animation is run under
     * @param attrs
     *            - set of attributes passed from system
     */
    public RpsAnimationSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
    }// ctor

    /**
     * Creates the animator for the object. If this method returns null, then it will
     * be necessary to invoke the 'setAnimator' method before the animation can start.
     * @return the animator
     */
    public Animator createAnimator() {
        return new RpsAnimator();
    }

}
