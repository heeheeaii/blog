
package com.nu.blog.modules.service.impl;

import com.nu.blog.base.lang.Consts;
import com.nu.blog.modules.repository.ChannelRepository;
import com.nu.blog.modules.service.ChannelService;
import com.nu.blog.modules.entity.Channel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class ChannelServiceImpl implements ChannelService {
	@Autowired
	private ChannelRepository channelRepository;

	@Override
	public List<Channel> findAll(int status) {
		Sort sort = Sort.by(Sort.Direction.DESC, "weight", "id");
		List<Channel> list;
		if (status > Consts.IGNORE) {
			list = channelRepository.findAllByStatus(status, sort);
		} else {
			list = channelRepository.findAll(sort);
		}
		return list;
	}

	@Override
	public Map<Integer, Channel> findMapByIds(Collection<Integer> ids) {
		List<Channel> list = channelRepository.findAllById(ids);
		if (null == list) {
			return Collections.emptyMap();
		}
		return list.stream().collect(Collectors.toMap(Channel::getId, n -> n));
	}

	@Override
	public Channel getById(int id) {
		return channelRepository.findById(id).get();
	}

	@Override
	@Transactional
	public void update(Channel channel) {
		Optional<Channel> optional = channelRepository.findById(channel.getId());
		Channel po = optional.orElse(new Channel());
		BeanUtils.copyProperties(channel, po);
		channelRepository.save(po);
	}

	@Override
	@Transactional
	public void updateWeight(int id, int weighted) {
		Channel po = channelRepository.findById(id).get();

		int max = Consts.ZERO;
		if (Consts.FEATURED_ACTIVE == weighted) {
			max = channelRepository.maxWeight() + 1;
		}
		po.setWeight(max);
		channelRepository.save(po);
	}

	@Override
	@Transactional
	public void delete(int id) {
		channelRepository.deleteById(id);
	}

	@Override
	public long count() {
		return channelRepository.count();
	}

}
