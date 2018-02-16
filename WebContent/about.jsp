
<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h4 class="modal-title">
					<span><img src="./custom/images/hadoop_fast.png"
						style='height: 10%; padding-right: 20px;' /> acinonyx</span>
				</h4>
			</div>
			<div class="modal-body">
				<p>About the Application</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<!-- Script for About Display -->
<script>
                $(document).ready(function(){
                    $("#myBtn").click(function(){
                        $("#myModal").modal("toggle");
                    });
                });
            </script>