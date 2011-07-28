loadBitmap({
	test : '/safrain/nodelet/test/resources/test.png',
	boom : '/safrain/nodelet/test/resources/boom.png',
	cruiser_normal : '/safrain/nodelet/test/resources/cruiser_normal.png',
	cruiser_destroyed : '/safrain/nodelet/test/resources/cruiser_destroyed.png',
	turret_normal : '/safrain/nodelet/test/resources/turret_normal.png',
	turret_destroyed : '/safrain/nodelet/test/resources/turret_destroyed.png',
	plasma : '/safrain/nodelet/test/resources/plasma.png'
});
var renderables = {
	test : Bitmap({
		bitmap : 'test',
		centerPivot : true
	}),
	boom : Animate({
		bitmap : 'boom',
		frameHeight : 20,
		centerPivot : true
	}),
	plamsa : Animate({
		bitmap : 'plasma',
		frameHeight : 15,
		loop : true,
		centerPivot : true
	})
};
var bullets = {
	testBullet : SimpleBullet({
		renderable : renderables.plamsa,
		collision : RoundCollision({
			radius : 7
		}),
		mover : Mover({
			maxSpeed : 2,
			acceleration : 0.1
		}),
		damage : 5,
		hitFX : FX({
			renderable : renderables.boom
		})
	})
}
var devices = {
	testHull : Hull({
		renderable : defaultBitmap('cruiser_normal'),
		destroyRenderable : defaultBitmap('cruiser_destroyed'),
		collision : RoundCollision({
			radius : 40
		}),
		hp : 10
	}),
	testWeapon : SimpleWeapon({
		position : Position({
			angle : 0
		}),
		renderable : defaultBitmap('turret_normal'),
		destroyRenderable : defaultBitmap('turret_destroyed'),
		collision : RoundCollision({
			radius : 20
		}),
		hp : 10,
		hitSequence : 1,
		invoker : ChargeInvoker({
			chargeRate : 5,
			maxCharge : 100
		}),
		bullet : bullets.testBullet,
		isAuto : true
	})
}
var ships = {
	testShip : Ship({
		position : Position({
			angle : 90
		}),
		mover : Mover({
			maxSpeed : 5,
			acceleration : 2,
			syncAngle : false
		}),
		devices : [ devices.testHull, devices.testWeapon ],
		destroyFX : FX({
			renderable : renderables.boom
		})
	})
}
var fxes = {};
var uis = {
	testUI : UI({
		position : Position({
			x : 100,
			y : 100
		}),
		width : 100,
		height : 100,
		renderable : Bitmap({
			bitmap : 'test',
			centerPivot : false
		})
	}),
	controlUI : ControlUI({
		width : 500,
		height : 500
	})
};
batchAddBuilder(renderables, Game.renderableFactory);
batchAddBuilder(devices, Game.gameFactory);
batchAddBuilder(bullets, Game.gameFactory);
batchAddBuilder(ships, Game.gameFactory);
batchAddBuilder(uis, Game.gameFactory);