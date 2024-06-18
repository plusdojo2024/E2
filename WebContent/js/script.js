/*�X�}�z�ŃX�N���[���������Ɉ�ԉ��Ƀt�b�^�[���\������鏈��*/
$(window).on("load resize", function () {
    let window_height = window.innerHeight
      ? window.innerHeight
      : $(window).innerHeight();
    $(".wrap").css("min-height", window_height + "px");
  });

/*�n���o�[�K���j���[��Ǐ]������@�\*/
$(function(){
  // �u���E�U���X�N���[��
  $(window).scroll(function(){
    // �X�N���[�����̏���
    $("nav").stop().animate({"top" : $(window).scrollTop()},1);
    $("label").stop().animate({"top" : $(window).scrollTop()},1);
  });
});

/*�X���C�h�̏��� */
$(function(){
  // �摜�̖���
  var count = $("#slide li").length;

  // ���ݕ\������Ă���摜�i�ŏ��͂P�Ԗڂ̉摜�j
  var current = 1;

  // ���ɕ\������摜
  var next = 2;

  // �t�F�[�h�C��/�A�E�g�̃C���^�[�o���i���b���Ƃɉ摜��؂�ւ��邩�B3000�~���b�ɐݒ�j
  var interval = 6000;

  // �t�F�[�h�C��/�A�E�g�̃X�s�[�h�i500�~���b�ɐݒ�j
  var duration = 100;

  // �^�C�}�[�p�̐ݒ�
  var timer;

  // �P�Ԗڂ̎ʐ^�ȊO�͔�\��
  $("#slide li:not(:first-child)").hide();

  // 3000�~���b�i�ϐ�interval�̒l�j���Ƃ�slideTimer()�֐������s
  timer = setInterval(slideTimer, interval);

  // slideTimer()�֐�
  function slideTimer(){
    // ���݂̉摜���t�F�[�h�A�E�g
    $("#slide li:nth-child(" + current + ")").fadeOut(duration);

    // ���̉摜���t�F�[�h�C��
    $("#slide li:nth-child(" + next + ")").fadeIn(duration);

    // �ϐ�current�̐V�����l�F�ϐ�next�̌��̒l
    current = next;

    // �ϐ�next�̐V�����l�F�ϐ�current�̐V�����l+1
    next = ++next;

    // �ϐ�next�̒l��3�i�摜�̑����j�𒴂���ꍇ�A1�ɖ߂�
    if(next > count){
      next = 1;
    }

    // target�N���X���폜
    $("#button li a").removeClass("target");

    // ���݂̃{�^����target�N���X��ǉ�
    $("#button li:nth-child(" + current + ") a").addClass("target");
  }

  // �{�^�����N���b�N
  $("#button li a").click(function(){
    // �e�L�X�g���e��ϐ�next�ɑ��
    next = $(this).html();

    // �^�C�}�[���~���čăX�^�[�g
    clearInterval(timer);
    timer = setInterval(slideTimer, interval);

    // ����̊֐����s
    slideTimer();

    return false;
  });
});