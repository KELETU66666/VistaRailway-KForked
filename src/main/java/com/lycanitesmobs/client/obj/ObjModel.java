package com.lycanitesmobs.client.obj;

import net.minecraft.client.renderer.vertex.VertexFormat;
import org.apache.commons.io.output.ByteArrayOutputStream;

import javax.vecmath.Vector2f;
import javax.vecmath.Vector4f;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/*
 * This class was created by <Lycanite>. It's distributed as
 * part of the LycanitesMob Mod. Get the Source Code in gitlab:
 * https://gitlab.com/Lycanite/LycanitesMobs
 *
 * LycanitesMob is Open Source and distributed under the
 * GPLv3 License: https://www.gnu.org/licenses/gpl-3.0.html
 */
public abstract class ObjModel extends Model
{

    public List<ObjObject> objObjects;

    protected String filename;
    
    ObjModel() {
        objObjects = new ArrayList<>();
    }
    
    public ObjModel(String classpathElem) {
        this();
        this.filename = classpathElem;
        if(filename.contains("/"))
            setID(filename.substring(filename.lastIndexOf("/")+1));
        else
            setID(filename);
    }
    
    protected byte[] read(InputStream resource) throws IOException {
        int i;
        byte[] buffer = new byte[65565];
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        while((i = resource.read(buffer, 0, buffer.length)) != -1)
        {
            out.write(buffer,0,i);
        }
        out.flush();
        out.close();
        return out.toByteArray();
    }

    public void renderGroup(ObjObject group) {
        this.renderGroup(group, new Vector4f(1, 1, 1, 1), new Vector2f(0, 0), null);
    }

    public void renderGroup(ObjObject group, Vector4f color, Vector2f textureOffset, VertexFormat vertexFormat) {
        //if(fireEvent(new ObjEvent(this, ObjEvent.EventType.PRE_RENDER_GROUP).setData(group, group)))
            this.renderGroupImpl(group, color, textureOffset, vertexFormat);
        //fireEvent(new ObjEvent(this, ObjEvent.EventType.POST_RENDER_GROUP).setData(group, group));
    }
    
    public void renderGroups(String groupsName) {
        if(fireEvent(new ObjEvent(this, ObjEvent.EventType.PRE_RENDER_GROUPS).setData(groupsName)))
            this.renderGroupsImpl(groupsName);
        fireEvent(new ObjEvent(this, ObjEvent.EventType.POST_RENDER_GROUPS).setData(groupsName));
    }
    
    public void render() {
        if(fireEvent(new ObjEvent(this, ObjEvent.EventType.PRE_RENDER_ALL)))
            this.renderImpl();
        fireEvent(new ObjEvent(this, ObjEvent.EventType.POST_RENDER_ALL));
    }
    
    protected abstract void renderGroupsImpl(String groupsName);
    
    protected abstract void renderGroupImpl(ObjObject objGroup, Vector4f color, Vector2f textureOffset, VertexFormat vertexFormat);

    protected abstract void renderImpl();
    
    public abstract boolean fireEvent(ObjEvent event);
}
