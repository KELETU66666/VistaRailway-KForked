package com.lycanitesmobs.client.obj;

/*
 * This class was created by <Lycanite>. It's distributed as
 * part of the LycanitesMob Mod. Get the Source Code in gitlab:
 * https://gitlab.com/Lycanite/LycanitesMobs
 *
 * LycanitesMob is Open Source and distributed under the
 * GPLv3 License: https://www.gnu.org/licenses/gpl-3.0.html
 */
public class ObjEvent
{

    public static enum EventType
    {
        /**
         * No data | data.length = 0
         */
        PRE_RENDER_ALL(true),
        
        /**
         * data[0] = groupsName String | data.length = 1
         */
        PRE_RENDER_GROUPS(true), 
        
        /**
         * data[0] = current object ObjObject | data[1] = group to renderer ObjGroup| data.length = 2
         */
        PRE_RENDER_GROUP(true),
        
        /**
         * No data | data.length = 0
         */
        POST_RENDER_ALL(false), 
        
        /**
         * data[0] = groupsName String | data.length = 1
         */
        POST_RENDER_GROUPS(false), 
        
        /**
         * data[0] = current object ObjObject | data[1] = group to renderer ObjGroup| data.length = 2
         */
        POST_RENDER_GROUP(false);

        private boolean cancel;

        EventType(boolean cancelable)
        {
            this.cancel = cancelable;
        }
        
        public boolean isCancelable()
        {
            return cancel;
        }
    }
    
    public ObjModel model;
    public EventType type;
    public Object[] data;

    public ObjEvent(ObjModel caller, EventType type)
    {
        this.data = new Object[0];
        this.model = caller;
        this.type = type;
    }
    
    public boolean canBeCancelled()
    {
        return type.isCancelable();
    }

    public ObjEvent setData(Object... data)
    {
        this.data = data;
        return this;
    }
}
