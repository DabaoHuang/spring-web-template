hahahaha = require './public/Lib.js'
console.log(hahahaha);
EXAMPLE = ((window, document)->
	text = ''
	print = ()-> console.log(text);
	return module =
		print : ()->  print() if text
		init : (text)->
			text = text
			@
)(window, document)