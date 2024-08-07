/*
 * Copyright (c) 2024 BookkeepersMC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */
package com.bookkeepersmc.loader.impl.util.mappings;

import net.fabricmc.mappingio.tree.MappingTree;
import org.spongepowered.asm.mixin.extensibility.IRemapper;

public class MixinRemapper implements IRemapper {
	protected final MappingTree mappings;
	protected final int fromId;
	protected final int toId;

	public MixinRemapper(MappingTree mappings, int fromId, int toId) {
		this.mappings = mappings;
		this.fromId = fromId;
		this.toId = toId;
	}

	@Override
	public String mapMethodName(String owner, String name, String desc) {
		final MappingTree.MethodMapping method = mappings.getMethod(owner, name, desc, fromId);
		return method == null ? name : method.getName(toId);
	}

	@Override
	public String mapFieldName(String owner, String name, String desc) {
		final MappingTree.FieldMapping field = mappings.getField(owner, name, desc, fromId);
		return field == null ? name : field.getName(toId);
	}

	@Override
	public String map(String typeName) {
		return mappings.mapClassName(typeName, fromId, toId);
	}

	@Override
	public String unmap(String typeName) {
		return mappings.mapClassName(typeName, toId, fromId);
	}

	@Override
	public String mapDesc(String desc) {
		return mappings.mapDesc(desc, fromId, toId);
	}

	@Override
	public String unmapDesc(String desc) {
		return mappings.mapDesc(desc, toId, fromId);
	}
}
